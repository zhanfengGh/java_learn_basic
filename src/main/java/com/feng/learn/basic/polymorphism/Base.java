package com.feng.learn.basic.polymorphism;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
public class Base {
    private String name;
    private String str;
    public Base() {

    }

    public Base(String name) {
        this.name = name;
    }
}
