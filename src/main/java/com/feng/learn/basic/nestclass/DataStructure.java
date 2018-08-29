package com.feng.learn.basic.nestclass;
/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
public class DataStructure {

    // Create an array
    private final static int SIZE = 15;
    private final int[] arrayOfInts = new int[DataStructure.SIZE];


    public DataStructure() {
        // fill the array with ascending integer values
        for (int i = 0; i < DataStructure.SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }


    public static boolean isEvenIndex(final int index) {
        return index % 2 == 0;
    }

    public static boolean isOddIndex(final int index) {
        return index % 2 != 0;
    }


    public void print(final Function<Integer, Boolean> iterator) {
        for (int i = 0; i < SIZE; i++) {
            if (iterator.apply(i)) {
                System.out.print(arrayOfInts[i] + " ");
            }
        }
        System.out.println();
    }

    public void print(final DataStructureIterator dsi) {
        while (dsi.hasNext()) {
            System.out.print(dsi.next() + " ");
        }
        System.out.println();
    }

    public void printEven() {

        // Print out values of even indices of the array
        final DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    // Inner class implements the DataStructureIterator interface,
    // which extends the Iterator<Integer> interface

    interface DataStructureIterator extends java.util.Iterator<Integer> {
    }

    private class EvenIterator implements DataStructureIterator {

        // Start stepping through the array from the beginning
        private int nextIndex = 0;

        @Override
        public boolean hasNext() {

            // Check if the current element is the last in the array
            return (nextIndex <= DataStructure.SIZE - 1);
        }

        @Override
        public Integer next() {

            // Record a value of an even index of the array
            final Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            // Get the next even element
            nextIndex += 2;
            return retValue;
        }
    }
}
