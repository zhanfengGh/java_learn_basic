package com.feng.learn.basic.interfacetest;

public class C2 extends C1 {

    //@Override
    public static void c() {
        System.out.println("C2.c()");
    }

    public static void main(String[] args) {
        C2 c = new C2();
        c.c();
        C1 c1 = c;
        c1.c();
    }

    @Override
    public AB ab() {
        return null;
    }
}
