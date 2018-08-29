package com.feng.learn.basic.exception;

public class Test {

    public static int test1() {
        try {
            doSomething();
            System.out.println("try");
            return 0;
        } catch (final Exception e) {
            System.out.println("catch");
            return -1;
        } finally {
            System.out.println("finally");
            return 1;
        }
    }

    private static void doSomething() {
        throw new RuntimeException();
    }

    public static void main(final String... args) {
        final int a = test1();
        System.out.println(a);
    }
}
