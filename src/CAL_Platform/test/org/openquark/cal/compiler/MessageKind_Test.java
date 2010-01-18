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
 * MessageKind_Test.java
 * Creation date: May 31, 2005.
 * By: Joseph Wong
 */
package org.openquark.cal.compiler;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * A set of JUnit test cases for the MessageKind class and its various subclasses.
 *
 * @author Joseph Wong
 */
public class MessageKind_Test extends TestCase {
    
    /**
     * @return (List of Class) A list of classes which do not have localized messages associated with them.
     */
    private static List<Class<?>> findErrors() {
        List<Class<?>> errorList = new ArrayList<Class<?>>();
        Class<MessageKind> thisClass = MessageKind.class;
        
        Class<?>[] messageTypeList = thisClass.getDeclaredClasses();
        for (final Class<?> currentMessageType : messageTypeList) {
            Class<?>[] messageList = currentMessageType.getDeclaredClasses();
            for (final Class<?> currentMessage : messageList) {
                String messageName = MessageKind.getClassName(currentMessage);
                if (!CALMessages.hasString(messageName) && !messageName.equals("DebugMessage")) {
                    errorList.add(currentMessage);
                }
            }
        }
        
        return errorList;
    }
    
    /**
     * Tests that no message kinds are missing the corresponding localized messages.
     */
    public void testNoMissingMessages() {
        List<Class<?>> errorClasses = findErrors();
        if (errorClasses.size() != 0) {
            StringBuilder messageBuffer = new StringBuilder();
            messageBuffer.append("The following MessageKind subclasses do not have localized messages:\n");
            for (final Class<?> nextClass : errorClasses) {
                messageBuffer.append(nextClass).append('\n');
            }
            Assert.fail(messageBuffer.toString());
        }
    }
}
