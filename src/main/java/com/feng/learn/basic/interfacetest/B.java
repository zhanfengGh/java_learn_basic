package com.feng.learn.basic.interfacetest;

public interface B {

    void a();

    void b();

    static void as() {
        System.out.println("B.as()");
    }

    default void ad() {
        System.out.println("B.ad()");
    };

    default void aaa() {

    }

}
