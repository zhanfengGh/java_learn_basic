package com.feng.learn.basic.classes;

public class Test {
    public static void main(String[] args) {
        A.C ac = new A.C();

        A a = new A();
        A.B ab = a.new B();
    }
}
