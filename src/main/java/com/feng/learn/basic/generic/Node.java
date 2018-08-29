package com.feng.learn.basic.generic;

public class Node<E> implements Comparable<E> {
    private E data;

    public void setData(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    @Override
    public int compareTo(E o) {
        return 0;
    }
}
