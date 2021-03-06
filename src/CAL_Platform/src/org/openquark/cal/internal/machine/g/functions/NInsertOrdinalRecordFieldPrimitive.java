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
 * NInsertOrdinalRecordFieldPrimitive.java
 * Created: Oct 12, 2005 
 * By: James Wright 
 */

package org.openquark.cal.internal.machine.g.functions;

import org.openquark.cal.compiler.QualifiedName;
import org.openquark.cal.internal.machine.g.Executor;
import org.openquark.cal.internal.machine.g.NPrimitiveFunc;
import org.openquark.cal.internal.machine.g.NRecordValue;
import org.openquark.cal.internal.machine.g.NValInt;
import org.openquark.cal.internal.machine.g.Node;
import org.openquark.cal.internal.module.Cal.Core.CAL_Dynamic_internal;
import org.openquark.cal.runtime.CALExecutorException;


/**
 * Implementation of insertOrdinalRecordFieldPrimitive
 */
public class NInsertOrdinalRecordFieldPrimitive extends NPrimitiveFunc {

    public static final QualifiedName name = CAL_Dynamic_internal.Functions.insertOrdinalRecordFieldPrimitive;
    public static final NInsertOrdinalRecordFieldPrimitive instance = new NInsertOrdinalRecordFieldPrimitive ();    
    
    private NInsertOrdinalRecordFieldPrimitive () {/* Constructor made private for creation control. */ }
        
    @Override
    protected int getArity () {
        return 3;
    }
   
    @Override
    protected QualifiedName getName () {
        return name;
    }
   
    @Override
    public Node doEvaluation (Node[] arguments, Executor executor)
            throws CALExecutorException {

        //extendRecordOrdinalPrimitive recordValue fieldName fieldValue
        NRecordValue recordValue = (NRecordValue) executor.internalEvaluate(arguments[0]);
        int fieldOrdinal = ((NValInt)executor.internalEvaluate(arguments[1])).getIntValue();
        String fieldName = "#" + fieldOrdinal;
        Node fieldValue = arguments[2];
        
        return recordValue.insertRecordField(fieldName, fieldValue);
    }
    
 
}
