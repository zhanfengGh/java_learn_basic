package com.feng.learn.basic.interfacetest;

public interface A {

    static void as() {
        System.out.println("A.as()");
    }

    void a();

    void b();

    default void ad() {
        System.out.println("A.ad()");
    }

}
