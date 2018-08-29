package com.feng.learn.basic;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

public class MainTest {
    public static final String TYPE = "SERVER_OPERATE|getDeviceIds|getDeviceNum|isDeviceOnline|ping|SERVER_CONFIG";

    public static final int _2000_SECONDS = 949334400;

    @Test
    public void intMax() {
        System.out.print(new Date(Long.MAX_VALUE));
    }

    @Test
    public void test1() {
        System.out.println(Math.pow(10, 9) < Integer.MAX_VALUE);
    }

    @Test
    public void test2() {
        Calendar c = Calendar.getInstance();
        c.set(2000, 1, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        int _2000 = (int) (c.getTimeInMillis() / 1000);
        System.out.println(_2000);
        Date dateMax = new Date((Integer.MAX_VALUE * 1L + _2000_SECONDS) * 1000L);
        System.out.println(dateMax);
    }


    @Test
    public void test3() {
        Properties properties = System.getProperties();
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            Object o = keys.nextElement();
            System.out.println(o + ": " + properties.get(o));
        }
    }

    @Test
    public void test4() {
        String[] split = TYPE.split("\\|");
        System.out.println();
    }
}
