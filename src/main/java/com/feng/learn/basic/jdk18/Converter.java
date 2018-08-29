package com.feng.learn.basic.jdk18;

@FunctionalInterface
public interface Converter<S, T> {
    T convert(S s);
}
