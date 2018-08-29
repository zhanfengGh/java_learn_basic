package com.feng.learn.basic.generic;

public class GenericMethod {

    public static <T> void swapArrayIndex(T[] array, int indexOne, int indexTwo) {
        T tmp = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = tmp;
    }
}
