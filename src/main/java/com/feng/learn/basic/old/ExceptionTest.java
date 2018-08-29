package com.feng.learn.basic.old;

public class ExceptionTest {


    public static void main(String[] args) {
        System.out.println(test());
    }

    private static int test() {
        int  i = 1;
        try {
            return ++i;
        } finally {
            System.out.println(i);
            return ++i;
        }
    }
}
