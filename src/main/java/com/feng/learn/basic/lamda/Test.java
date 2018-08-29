package com.feng.learn.basic.lamda;

import java.util.function.Predicate;

public class Test {


    public static void test(A a) {
        System.out.println(a.a(5, 10));
    }


    public static void testPre(Predicate<String> tester) {

    }

    static void testPre2(Predicate2<String> tester) {
        if (tester.test("ss")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
    public static void main(String[] args) {
        test(new A() {

            @Override
            public int a(int i, int j) {
                return i + j;
            }
        });

        test((i, j) -> {
            return i * j;
        });

        test((i, j) -> i / j);


        Predicate<String> stringPredicate = (str) -> str.equals("");
        stringPredicate.negate().and((str) -> str.equals("  "));

        testPre(((str) -> str.equals("")));


        Predicate2<String> pre2 = (t) -> t.equals("");
        pre2 = pre2.negate();
        testPre2(pre2);
    }
}
