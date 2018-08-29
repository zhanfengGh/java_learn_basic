package com.feng.learn.basic.old2; /**
 *
 */

/**
 * @author zhangzhanfeng
 * @date Aug 25, 2017
 */
public class Person {
    private double weight;

    {
        weight = 3.0;
    }

    {
        System.out.println(weight);
        weight = 4.0;
        System.out.println(weight);
    }

    public Person() {
        System.out.println(weight);
    }

    public static void main(String[] args) {
        Person p = new Person();
    }
}
