package com.feng.learn.basic.lamda;

import java.util.function.Consumer;

public class LambdaScopeTest {

    private int x = 0;

    class FirstLevel{
        private int x = 1;

        void test2() {
            System.out.println("test2");
        }

        void test(int x) {
            Consumer<Integer> c = integer -> {
                System.out.println(integer);
                System.out.println(x);
                System.out.println(this.x);
                System.out.println(LambdaScopeTest.this.x);
                test2();
            };
            c.accept(x);
        }
    }

    public static void main(String... args) {
        LambdaScopeTest lambdaScopeTest = new LambdaScopeTest();
        FirstLevel firstLevel = lambdaScopeTest.new FirstLevel();
        firstLevel.test(5);
    }
}
