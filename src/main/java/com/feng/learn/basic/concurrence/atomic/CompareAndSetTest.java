package com.feng.learn.basic.concurrence.atomic;

public class CompareAndSetTest {
    private int number;

    public synchronized int getNumber() {
        return this.number;
    }

    private synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = this.number;
        if (oldValue == expectedValue) {
            this.number = newValue;
        }
        return oldValue;
    }

    public boolean compareAndSet(int expectedValue, int newValue) {
        return compareAndSwap(expectedValue, newValue) == expectedValue;
    }

    public int increment() {
        int value;
        for (; ; ) {  //循环
            value = getNumber();
            if (compareAndSet(value, value + 1)) {
                return value + 1; //回退
            }
        }

    }


}
