package com.feng.learn.basic.interfacetest;

public class AB extends C implements A, B {

    public static void main(final String[] args) {
        A.as();
        B.as();
        final AB ab = new AB();

        ab.a();
        final A a = ab;
        final B b = ab;
        a.a();
        b.a();

        a.ad();
        b.ad();

        ab.aaa();
        b.aaa();
    }

    @Override
    public void a() {
        System.out.println("AB.a()");
    }

    @Override
    public void b() {
        System.out.println("AB.b()");
    }

    public void as() {

    }

    @Override
    public void ad() {
        A.super.ad();
        B.super.ad();
        System.out.println("AB.ad()");
    }
}
