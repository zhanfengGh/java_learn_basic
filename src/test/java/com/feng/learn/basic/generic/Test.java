package com.feng.learn.basic.generic;


import java.util.Arrays;

public class Test {

    @org.junit.Test
    public void test1() {
        Integer[] array = {1, 2, 3, 4};
        Double[] ad = {1.0, 2.0, 3.0, 4.0};
        String[] as = {"1", "2", "3", "4"};

        GenericMethod.swapArrayIndex(as, 0, 2);
        GenericMethod.swapArrayIndex(array, 0, 2);
        GenericMethod.swapArrayIndex(ad, 0, 2);

    }
}
