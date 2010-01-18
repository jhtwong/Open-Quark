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
 * RTInsertOrdinalRecordFieldPrimitive.java
 * Created: October 11, 2005
 * By: James Wright
 */

package org.openquark.cal.internal.runtime.lecc.functions;

import org.openquark.cal.internal.runtime.lecc.LECCMachineConfiguration;
import org.openquark.cal.internal.runtime.lecc.RTExecutionContext;
import org.openquark.cal.internal.runtime.lecc.RTFullApp;
import org.openquark.cal.internal.runtime.lecc.RTRecordValue;
import org.openquark.cal.internal.runtime.lecc.RTResultFunction;
import org.openquark.cal.internal.runtime.lecc.RTSupercombinator;
import org.openquark.cal.internal.runtime.lecc.RTValue;
import org.openquark.cal.internal.runtime.lecc.RTData.CAL_Int;
import org.openquark.cal.runtime.CALExecutorException;
import org.openquark.cal.runtime.CalValue;



/**
 * Implement the primitive function insertOrdinalRecordFieldPrimitive defined in the Prelude.
 *
 * primitive private insertOrdinalRecordFieldPrimitive :: {r} -> Int -> a -> Prelude.CalValue;
 *
 * Return value is an RTValue.
 *
 * @author Jawright
 */
public final class RTInsertOrdinalRecordFieldPrimitive extends RTSupercombinator {
    public static final RTInsertOrdinalRecordFieldPrimitive $instance = new RTInsertOrdinalRecordFieldPrimitive();

    private RTInsertOrdinalRecordFieldPrimitive() {
        // Empty constructor created for access control.
    }

    public static final RTInsertOrdinalRecordFieldPrimitive make(RTExecutionContext $ec) {
        return RTInsertOrdinalRecordFieldPrimitive.$instance;
    }

    @Override
    public int getArity() {
        return 3;
    }

    @Override
    public final RTValue f(final RTResultFunction rootNode, final RTExecutionContext $ec) throws CALExecutorException {
        // Arguments
        RTValue fieldValue = rootNode.getArgValue();
        RTValue currentRootNode;
        RTValue fieldOrdinal = (currentRootNode = rootNode.prevArg()).getArgValue();
        RTValue recordValue = currentRootNode.prevArg().getArgValue();

        // Release the fields in the root node to open them to garbage collection
        rootNode.clearMembers();

        return f3S(
                RTValue.lastRef(recordValue.evaluate($ec), recordValue),
                RTInsertOrdinalRecordFieldPrimitive.lastRef(fieldOrdinal.evaluate($ec).getIntValue(), fieldOrdinal = null),
                RTValue.lastRef(fieldValue, fieldValue = null), $ec);
    }

    @Override
    public final RTValue f3L(RTValue recordValue, RTValue fieldOrdinal, RTValue fieldValue, RTExecutionContext $ec) throws CALExecutorException {
        return f3S(
                RTValue.lastRef(recordValue.evaluate($ec), recordValue),
                RTInsertOrdinalRecordFieldPrimitive.lastRef(fieldOrdinal.evaluate($ec).getIntValue(), fieldOrdinal = null),
                RTValue.lastRef(fieldValue, fieldValue = null), $ec);
    }

    public final RTValue f3S(RTValue recordValue, int fieldOrdinal, RTValue fieldValue, RTExecutionContext $ec) {

        $ec.incrementNMethodCalls();
        if (LECCMachineConfiguration.generateDebugCode() && $ec.isDebugProcessingNeeded(getQualifiedName())) {
            $ec.debugProcessing(getQualifiedName(), new RTValue[]{recordValue, CAL_Int.make(fieldOrdinal), fieldValue});
        }

        return ((RTRecordValue)recordValue).insertOrdinalField(fieldOrdinal, fieldValue);
    }

    @Override
    public final String getModuleName() {
        //JUnit tested to equal its binding file value in RuntimeStringConstantsTest.
        return "Cal.Core.Dynamic";
    }

    @Override
    public final String getUnqualifiedName() {
        //JUnit tested to equal its binding file value in RuntimeStringConstantsTest.
        return "insertOrdinalRecordFieldPrimitive";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getQualifiedName() {
        //JUnit tested to equal its binding file value in RuntimeStringConstantsTest.
        return "Cal.Core.Dynamic.insertOrdinalRecordFieldPrimitive";
    }

    /**
     * lastRef is used as a means of nulling out local fields/variables
     * at the same time they are being passed to as arguments in a method call.
     * For example:
     *      myclass.myMethod(RTValue.lastRef(arg, arg = null));
     * This is, of course, only used if there are no further references to the
     * local in the code containing the method call.
     *
     * In essence this removes the local reference after it has been passed to
     * myMethod but before myMethod is evaluated.
     *
     * The purpose behind these contortions is to make the object passed as a
     * method argument available for garbage collection.  Normally the local
     * reference would prevent garbage collection until the call to myMethod
     * returned and the local reference could be nulled out.
     *
     *  If the argument is the root node of a dynamically expanded data structure
     *  (ex. a List) the reference to the root node will keep the entire structure
     *  in memory, even if the code in myMethod releases its reference to the root
     *  as it traverses the data structure.  By nulling out the local using lastRef
     *  the garbage collector can collect the traversed portion of the data
     *  structure, potentially significantly reducing the memory used.
     *
     * @param i
     * @param v
     * @return The value of the first argument.
     */
    private static final int lastRef (int i, RTValue v) {
        return i;
    }

    /**
     * A fully saturated application of insertOrdinalRecordFieldPrimitive.
     * Note:  This class doesn't need to implement getArity() or getArgCount()
     * as a fully saturated application can be treated like a zero arity function
     * and the base implementation of both these methods returns zero.
     * @author rcypher
     */
    public static final class RTAppS extends RTFullApp {
        private final RTInsertOrdinalRecordFieldPrimitive function;
        private RTValue recordValue;
        private int fieldOrdinal;
        private RTValue fieldValue;


        public RTAppS(RTInsertOrdinalRecordFieldPrimitive function, RTValue recordValue, int fieldOrdinal, RTValue fieldValueThunk) {
            assert (function != null && recordValue != null && fieldValueThunk != null) :
                "Null argument in constructor for RTInsertOrdinalRecordFieldPrimitive.RTAppL";

            this.function = function;
            this.recordValue = recordValue;
            this.fieldOrdinal = fieldOrdinal;
            this.fieldValue = fieldValueThunk;
        }

        @Override
        protected final RTValue reduce(RTExecutionContext $ec) {
            if (result == null) {
                setResult(function.f3S(recordValue, fieldOrdinal, fieldValue, $ec));
                clearMembers();
            }
            return result;
        }

        @Override
        public final void clearMembers() {
            //we don't need to clear this.function, since it points to a singleton held by a static member field
            recordValue = null;
            fieldValue = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final int debug_getNChildren() {
            if (result != null) {
                return super.debug_getNChildren();
            }
            return 3;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public final CalValue debug_getChild(int childN) {
            if (result != null) {
                return super.debug_getChild(childN);
            }

            switch (childN) {
            case 0:
                return recordValue;
            case 1:
                return CAL_Int.make(fieldOrdinal);
            case 2:
                return fieldValue;
            default:
                throw new IndexOutOfBoundsException();
            }
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public final String debug_getNodeStartText() {
            if (result != null) {
                return super.debug_getNodeStartText();
            }

            return "(" + function.getQualifiedName();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public final String debug_getNodeEndText() {
            if (result != null) {
                return super.debug_getNodeEndText();
            }

            return ")";
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public final String debug_getChildPrefixText(int childN) {
            if (result != null) {
                return super.debug_getChildPrefixText(childN);
            }

            if (childN >= 0 && childN < 3) {
                return " ";
            }

            throw new IndexOutOfBoundsException();
        }

    }

 }
