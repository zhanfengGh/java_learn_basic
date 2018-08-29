package com.feng.learn.basic.array;

import java.util.ArrayList;
import java.util.List;

public class ArraySize {

    public static void main(String[] args) throws InterruptedException {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(new int[1]);
        }
        System.out.println();
        synchronized (ArraySize.class) {
            ArraySize.class.wait();
        }
    }
}
