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
 * SynchronizedQueue.java
 * Created: Mar 7, 2007
 * By: mbyne
 */
package org.openquark.cal.benchmarks.shootout;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to pass values between values between 
 * different CAL programs running concurrently. if values are produced
 * more quickly than they are consumed the queue will grow, and its size
 * is not limited.
 *  
 * @author Magnus Byne
 */
public class SynchronizedQueue {
    List<Integer> queue;

    public SynchronizedQueue(int size) {
        queue = new ArrayList<Integer>(size);
    }
    
    /**
     * an an item to the queue
     * @param item to queue
     */
    public void enqueue(int item) {
        synchronized (queue) {
            queue.add(new Integer(item));
            if (queue.size() == 1) {
                queue.notify();
            }
        }
    }

    /**
     * remove an item form the queue. This will block
     * if the queue is empty.
     * @return item from the head of the queue
     */
    public int dequeue() {
        synchronized (queue) {
            while (queue.size() == 0) {
                try {
                    queue.wait();
                } catch (InterruptedException ex) {
                    System.err.println("Unexpected exception: " + ex);
                }
            }
            return queue.remove(0).intValue();
        } 
    }
}
