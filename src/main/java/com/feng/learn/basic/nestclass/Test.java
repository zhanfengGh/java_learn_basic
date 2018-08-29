package com.feng.learn.basic.nestclass;

import java.util.function.Function;

public class Test {
    public static void main2(final String... args) {
        final A a = new A();

        final A.B ab = a.new B();

    }

    public static void main(final String[] s) {

        // Fill the array with integer values and print out only
        // values of even indices
        final DataStructure ds = new DataStructure();
        ds.printEven();

        ds.print(new DataStructure.DataStructureIterator() {
            private int index = 1;

            @Override
            public boolean hasNext() {
                return index < ds.getArrayOfInts().length;
            }

            @Override
            public Integer next() {
                final Integer ret = ds.getArrayOfInts()[index];
                index += 2;
                return ret;
            }
        });

        final Function<Integer, Boolean> it = index -> index % 2 == 0;
        final Function<Integer, Integer> itw = it.andThen(flag -> flag ? 1 : 0);
        ds.print(index -> index % 2 == 0);
        ds.print(index -> index % 2 != 0);
        ds.print(index -> index % 3 != 0);


        ds.print(DataStructure::isEvenIndex);
        ds.print(DataStructure::isOddIndex);
    }
}
