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
 * GemConnectionEvent.java
 * Creation date: (02/26/02 3:59:00 PM)
 * By: Edward Lam
 */
package org.openquark.gems.client;

import java.util.EventObject;

/**
 * A connection event object
 * Creation date: (02/26/02 3:59:00 PM)
 * @author Edward Lam
 */
public class GemConnectionEvent extends EventObject {
    private static final long serialVersionUID = -4957061003855693436L;
    /** The type of event. */
    private final EventType eventType;

    /**
     * Constructor for a gem connection event.
     */
    public GemConnectionEvent(Connection source, EventType eventType) {
        super(source);
        this.eventType = eventType;
    }

    /**
     * Gets the type of event.
     * @return EventType the type
     */
    public final EventType getType() {
        return eventType;
    }

    /**
     * Returns a parameter string identifying this event.
     * This method is useful for event-logging and for debugging.
     *
     * @return a string identifying the event and its attributes
     */
    public String paramString() {
        return "GemConnectionEvent";
    }

    /**
     * Enumeration for gem connection events.
     * Creation date: (03/04/02 3:07:00 PM)
     */
    public static final class EventType {

        private final String typeString;

        private EventType(String s) {
            typeString = s;
        }

        /** Connection type. */
        public static final EventType CONNECTION = new EventType("CONNECTION");

        /** Disconnection type. */
        public static final EventType DISCONNECTION = new EventType("DISCONNECTION");
    
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return typeString;
        }
    }
}
