package com.feng.learn.basic.nestclass;

public enum Day {
    SUNDAY,
    MONDAY,
    THUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public static void main(final String... args) {
        for (final Day d : Day.values()) {
            System.out.println(d);
        }

        System.out.println(Day.SUNDAY.name());
    }

}
