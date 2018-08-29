package com.feng.learn.basic.polymorphism;

import lombok.Setter;

@Setter
public class Sub extends Base {
    private int name;
    private String str;

    public Sub() {

    }


    public Sub(int name) {
        super(String.valueOf(name));
        this.name = name;
    }

    public Sub(int name, String str) {
        super(String.valueOf(name));
        this.str = str;
    }

    public String getStr() {
        return this.str;
    }

    public static void main(String[] args) {
        Sub s = new Sub(888, "feng");
        String sStr = s.getStr();
        Base b = s;
        String name1 = b.getName();
        String bStr = b.getStr();
    }
}
