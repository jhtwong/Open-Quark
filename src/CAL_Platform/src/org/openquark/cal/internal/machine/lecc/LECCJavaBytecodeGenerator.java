/*
 * Copyright (c) 2007 BUSINESS OBJECTS SOFTWARE LIMITED
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *  
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *  
 *     * Neither the name of Business Objects nor the names of its contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *  
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */


/*
 * LECCJavaBytecodeGenerator.java
 * Creation date: Oct 18, 2006.
 * By: Edward Lam
 */
package org.openquark.cal.internal.machine.lecc;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.Deflater;

import org.openquark.cal.compiler.CompilerMessageLogger;
import org.openquark.cal.compiler.TypeConstructor;
import org.openquark.cal.internal.javamodel.AsmJavaBytecodeGenerator;
import org.openquark.cal.internal.javamodel.BytecodeDebuggingUtilities;
import org.openquark.cal.internal.javamodel.JavaClassRep;
import org.openquark.cal.internal.javamodel.JavaGenerationException;
import org.openquark.cal.internal.javamodel.JavaTypeName;
import org.openquark.cal.internal.machine.CodeGenerationException;
import org.openquark.cal.internal.machine.lecc.LECCModule.FunctionGroupInfo;
import org.openquark.cal.machine.AsynchronousFileWriter;
import org.openquark.cal.machine.ProgramResourceLocator;
import org.openquark.cal.machine.ProgramResourceRepository;
import org.openquark.cal.machine.StatusListener;


/**
 * @author Bo Ilic, Raymond Cypher
 */
public class LECCJavaBytecodeGenerator extends JavaGenerator {

    /** 
     * Calls a helper method which performs various checks on the generated bytecode. Most notable is a byte code verifier.
     * Individual tests and debug output can be turned on by setting the boolean variables in the debugGeneratedBytecode method below.
     * Note that these tests slow down bytecode generation considerably.
     */
    private static final boolean DEBUG_GENERATED_BYTECODE =  false;
    
    /** The deflater object.  
     * Do not create and free as needed, as this may lead to OutOfMemoryErrors if too many are created
     * (Sun bug id 4797189). */
    private final Deflater deflater = new Deflater();
    
    /** The folder to which to write the Java files for the current module. */
    private final ProgramResourceLocator.Folder moduleResourceFolder;
    
    /** The class loader for the module associated with this generator. */
    private final CALClassLoader classLoader;
    
    /** Flag indicating that generated classes should be preemptively loaded. */
    private final boolean immediateUse;
    
    /** Flag indicating that generated classes are for an adjunct. */
    private final boolean forAdjunct;
    
    private final LECCModule module;  
    
    /**
     * responsible for writing the class files generated on another serialization thread.
     * Note that this object is owned by the CodeGenerator and shared between all modules
     * being serialized.
     */
    private final AsynchronousFileWriter classFileWriter;
       
    /**
     * A set of the names of the class files generated by this generator.
     */
    private final Set<String> generatedClassFiles = new HashSet<String>();
    
    
    /**
     * Constructor for a LECCJavaBytecodeGenerator
     * @param module The module for which java classes will be generated.
     * @param resourceRepository The repository of program resources.
     * @param forAdjunct
     * @param immediateUse
     * @param classFileWriter
     * @throws IOException if there was a problem creating the repository folder where the source generation files will exist.
     */
    LECCJavaBytecodeGenerator(LECCModule module, ProgramResourceRepository resourceRepository, boolean forAdjunct, boolean immediateUse, AsynchronousFileWriter classFileWriter) 
    throws IOException {
        this.module = module;

        this.moduleResourceFolder = CodeGenerator.getModuleResourceFolder(module.getName());
        if (!immediateUse && !forAdjunct) {
            // Ensure the module repository folder exists.
            // throws IOException.
            resourceRepository.ensureFolderExists(moduleResourceFolder);
        }
        
        this.classLoader = module.getClassLoader();
        this.immediateUse = immediateUse;
        this.forAdjunct = forAdjunct;
        this.classFileWriter = classFileWriter;
    }    
    
    /** {@inheritDoc} */
    @Override
    void createFunction(FunctionGroupInfo functionGroupInfo, boolean forceWrite, CompilerMessageLogger logger)
            throws CodeGenerationException {
        if (forceWrite) {
            // Get the sc definition.
            JavaClassRep classRep = JavaDefinitionBuilder.getSCDefinition(functionGroupInfo, module, getCodeGenerationStats());
            
            // Generate and write classes.
            writeClassWithInnerClasses(classRep, logger);
            
            informStatusListeners(StatusListener.SM_ENTITY_GENERATED_FILE_WRITTEN, functionGroupInfo.getFunctionGroupName());
            
        } else {
            informStatusListeners(StatusListener.SM_ENTITY_GENERATED, functionGroupInfo.getFunctionGroupName());
        }
    }

    /** {@inheritDoc} */
    @Override
    void createTypeDefinition(TypeConstructor typeConstructor,
            boolean forceWrite, CompilerMessageLogger logger) throws CodeGenerationException {
        // Write out the bytecode for the type constructor and associated data constructors.
        if (forceWrite) {
            // Get the data type definition.
            JavaClassRep classRep = JavaDefinitionBuilder.getDataTypeDefinition(typeConstructor, module, getCodeGenerationStats());
            
            // Generate and write classes.
            writeClassWithInnerClasses(classRep, logger);
            
            for (int j = 0; j <= typeConstructor.getNDataConstructors(); ++j) {
                informStatusListeners(StatusListener.SM_ENTITY_GENERATED_FILE_WRITTEN, typeConstructor.getName().getUnqualifiedName());
            }
        } else {
            for (int j = 0; j <= typeConstructor.getNDataConstructors(); ++j) {
                informStatusListeners(StatusListener.SM_ENTITY_GENERATED, typeConstructor.getName().getUnqualifiedName());
            }
        }    

    }

    /** {@inheritDoc} */
    @Override
    void wrap() throws CodeGenerationException {
        // There is no cleanup/finalization for the bytecode generator to do at the end of generating code
        // for a module.
    }

    private void writeClassWithInnerClasses(JavaClassRep classRep, CompilerMessageLogger logger) throws CodeGenerationException {
        try {
            byte[] bytecode = AsmJavaBytecodeGenerator.encodeClass(classRep);
            writeClass(classRep, bytecode, logger);
            
            for (int i = 0, nInnerClasses = classRep.getNInnerClasses(); i < nInnerClasses; ++i) {
                JavaClassRep innerClassRep = classRep.getInnerClass(i);
                writeClassWithInnerClasses(innerClassRep, logger);
            }        
        } catch (JavaGenerationException e) {
            throw new CodeGenerationException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * Write out a class to a file.
     * @param classRep the source model representation of the class
     * @param bytecode the generated byte code for the class i.e. what actually to save.
     * @param logger the logger to use for logging error messages.
     */
    private void writeClass(JavaClassRep classRep, byte[] bytecode, CompilerMessageLogger logger) {
            
        // figure out the unqualified name of the class.       
        JavaTypeName typeName = classRep.getClassName();       
        String className = typeName.getUnqualifiedJavaSourceName().replace('.', '$');
        
        
        if (immediateUse) {
            classLoader.defineClass(typeName.getName(), bytecode, forAdjunct);
            
        } else {
            
            // Deflate the class data.
            // Note that it is better to perform deflation on the current thread than to get 
            //  the asynchronous file writer thread to do it, since that thread is already the bottleneck.
            
            deflater.reset();
            deflater.setInput(bytecode);
            deflater.finish();
            
            byte[] buf = new byte[bytecode.length];
            while (!deflater.finished()) {
                deflater.deflate(buf);
            }
            
            int deflatedSize = deflater.getTotalOut();
            byte[] dataToWrite = new byte[deflatedSize];
            System.arraycopy(buf, 0, dataToWrite, 0, deflatedSize);
            
            String fileName = className + ".lc";
            ProgramResourceLocator.File classFileLocator = moduleResourceFolder.extendFile(fileName);
            
            //adds to the class writing queue and returns immediately
            //saving of class files is done on another thread.
            classFileWriter.addFileToWrite(new AsynchronousFileWriter.FileData(classFileLocator, dataToWrite), logger);
            
            // Add to the set of generated class files.
            generatedClassFiles.add (fileName);
        }
        
        if (DEBUG_GENERATED_BYTECODE) {
            debugGeneratedBytecode(typeName, bytecode);
        }        
    }

    /**
     * Get the class data for a cal entity by name.
     * @param module the module in which the entity exists.
     * @param unqualifiedClassName the name of the class representing the entity.
     * @return the class data, or null if the name is not the name of a class representing an entity generated in this module.
     * @throws CodeGenerationException 
     */
    static byte[] generateClassData(LECCModule module, String unqualifiedClassName) throws CodeGenerationException {
        JavaClassRep classRep = JavaDefinitionBuilder.getClassRep(module, unqualifiedClassName);
        if (classRep == null) {
            return null;
        }
        try {
            byte[] bytecode = AsmJavaBytecodeGenerator.encodeClass(classRep);
            if (DEBUG_GENERATED_BYTECODE) {
                debugGeneratedBytecode(classRep.getClassName(), bytecode);
            }
            
            return bytecode;
        } catch (JavaGenerationException e) {
            throw new CodeGenerationException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * @return Returns the generatedClassFiles.
     */
    @Override
    Set<String> getGeneratedClassFiles() {
        return new HashSet<String> (generatedClassFiles);
    }
    
    /** 
     * Called if the static flag AsmJavaBytecodeGenerator.DEBUG_GENERATED_BYTECODE is set.
     * 
     * Performs various checks on the generated bytecode. Most notable is a byte code verifier.
     * Individual tests and debug output can be turned on by setting the boolean variables in the
     * method body below. Note that these tests slow down bytecode generation considerably.
     * 
     * Note: path names below are Windows only and you'll have to adjust according to your own machine.
     * This is for debug purposes only.
     * 
     * @param typeName
     * @param bytecode
     */
    private static void debugGeneratedBytecode(JavaTypeName typeName, byte[] bytecode) {
        
        final boolean dumpAsmifiedText = true;
        final boolean dumpDisassembledText = true;
        final boolean verifyClassFileFormat = true;
        
        //the ASM bytecode generator doesn't currently generate any debug op codes.
        final boolean skipDebugOpCodes = false;
        
        final boolean skipInnerClassAttributes = false;
              
        String className = typeName.getUnqualifiedJavaSourceName().replace('.', '$');
        String packageName = typeName.getPackageName();
        int lastDot = packageName.lastIndexOf('.');
        String moduleName = packageName.substring(lastDot + 1).replaceAll("^cal_", "");
                
        if (dumpAsmifiedText) {
            String asmifierDumpPath = "d:\\dev\\asmifierOutput\\asm\\" + moduleName + "\\" + className + ".txt";  
            BytecodeDebuggingUtilities.dumpAsmifiedText(asmifierDumpPath, bytecode, skipDebugOpCodes, skipInnerClassAttributes);
        }
        
        if (dumpDisassembledText) {
            String disassembyDumpPath = "d:\\dev\\disassembly\\asm\\" + moduleName + "\\" + className + ".txt";
            BytecodeDebuggingUtilities.dumpDisassembledText(disassembyDumpPath, bytecode, skipDebugOpCodes, skipInnerClassAttributes);
        }
        
        if (verifyClassFileFormat) {
            BytecodeDebuggingUtilities.verifyClassFileFormat(moduleName + "\\" + className + ".class", bytecode);
        }
    }
    
}