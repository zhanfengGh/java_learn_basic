package com.feng.learn.basic.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MainTest {

    @Test
    public void testInit() {
        int[] array = new int[5];
        int[] array2 = new int[]{1, 2, 3};
        int[] array23 = {4, 5};
    }


    @Test
    public void testString() {
        char c = '\u0000';
        String str = "\u0000";
        System.out.println("a" + c + "a");
        System.out.println("A" + str + "A");
    }

    @Test
    public void testClassInit() {
        Class1 c1 = new Class1();
    }

    @Test
    public void testImutableString() {
        String str = " str1";
        System.out.println(System.identityHashCode(str));
        str = str + "2";
        System.out.println(System.identityHashCode(str));

    }

    @Test
    public void testTypeAutoUp() {
        short s = 5;
        short s2 = 5;

        short s3 = (short) (s + s2);

        char c = 'q';
        char c2 = 'a';
        char c3 = (char) (c + c2);

        byte b = 1;
        byte b2 = 2;
        byte b3 = (byte) (b + b2);
    }

    @Test
    public void testSwitch9() {
        switch ("h") {
            case "h":
                break;
            default:

        }
    }

    @Test
    public void testInstanceof() {
        System.out.println(null instanceof String);
    }
}

class Class1 {
    private static String str1 = "str1";
    private static String str2 = "str2";

    static {
        System.out.println(str1);
        System.out.println(Class1.str2);
    }

    {
        System.out.println(str2);
    }
}

