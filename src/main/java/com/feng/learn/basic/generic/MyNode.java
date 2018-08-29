package com.feng.learn.basic.generic;

public class MyNode extends Node<Integer> {

    @Override
    public void setData(Integer data) {
        System.out.println("MyNode.setData()");
        super.setData(data);
    }

    @Override
    public Integer getData() {
        return super.getData();
    }

    public static void main(String[] args) {
        MyNode mn = new MyNode();
        Node n = mn;
        n.setData("String");
        mn.setData(5);
        Integer data = mn.getData();
    }
}
