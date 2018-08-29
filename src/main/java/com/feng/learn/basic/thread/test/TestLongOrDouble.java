package com.feng.learn.basic.thread.test;

public class TestLongOrDouble {

    private static final class Entry {
        private long l;
        private double d;
    }

    public static void main(String[] args) {
        final Entry e = new Entry();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    e.l = 1;
                    long lng = e.l;
                    if (lng != 0 && lng != 1 && lng != 2) {
                        System.out.println("long:" + lng);
                    }
                    e.d = 1.0;
                    double dbl = e.d;
                    if (dbl != 0.0 && dbl != 1.0 && dbl != 2.0) {
                        System.out.println("double:" + dbl);
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    e.l = 2;
                    long lng = e.l;
                    if (lng != 0 && lng != 1 && lng != 2) {
                        System.out.println(lng);
                    }
                    e.d = 2.0;
                    double dbl = e.d;
                    if (dbl != 0.0 && dbl != 1.0 && dbl != 2.0) {
                        System.out.println(dbl);
                    }
                }
            }
        }.start();
    }

}
