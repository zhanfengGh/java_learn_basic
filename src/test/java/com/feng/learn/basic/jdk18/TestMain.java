package com.feng.learn.basic.jdk18;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
public class TestMain {

    @Test
    public void testLambda() {
        List<String> list = Arrays.asList("Hello", "world", "java");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        list.sort((String s1, String s2) -> {
            return s1.compareTo(s2);
        });

        list.sort((s1, s2) -> s1.compareTo(s2));
    }

    @Test
    public void testMethodReference() {
        Converter<String, Long> c = new Converter<String, Long>() {
            @Override
            public Long convert(String s) {
                return Long.valueOf(s);
            }
        };
        long ret = c.convert("10");

        c = (s) -> Long.valueOf(s);
        ret = c.convert("5");

        c = Long::valueOf;
        ret = c.convert("15");

        c = Long::new;
        ret = c.convert("20");
    }

    @Test
    public void testPredicate() {
        boolean b;
        Predicate<Object> p = new Predicate<Object>() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };
        p = (o) -> false;

        b = p.test("Str");
        b = p.negate().test("Str");

        Function<String, Long> f = s -> Long.valueOf(s);
        f = Long::new;
        f = Long::valueOf;
        f.apply("222");

    }

    @Test
    public void testSearchList() {
        final Integer NUM = 1000000;
        //List<Integer> list = new ArrayList<>(NUM);
        List<Integer> list = new LinkedList<>();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < NUM; i++) {
            list.add(i);
        }
        long t2 = System.currentTimeMillis();
        log.info("create use time: {}", t2 - t1);
        long t3 = System.currentTimeMillis();
        list.contains(NUM); // 内部使用for(int i = 0; i < list.size(); i++){}
        long t4 = System.currentTimeMillis();
        log.info("search use time: {}", t4 - t3);

        long t5 = System.currentTimeMillis();
        for (Integer i : list) {
            if (i.equals(NUM)) {
                break;
            }
        }
        long t6 = System.currentTimeMillis();
        log.info("for each use time: {}", t6 - t5);

        long t7 = System.currentTimeMillis();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equals(NUM)) {
                break;
            }
        }
        long t8 = System.currentTimeMillis();
        log.info("iterator use time: {}", t8 - t7);

    }
}

