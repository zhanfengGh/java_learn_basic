package com.feng.learn.basic.lamda;

public interface Predicate2<T> {

    boolean test(T t);

    default Predicate2<T> negate() {
        return t -> !Predicate2.this.test(t);
    }

    default Predicate2<T> and(Predicate<? super T> other) {
        return (t) -> {
            return test(t) && other.test(t);
        };
    }

    default Predicate2<T> or(Predicate<? super T> other) {
        return new Predicate2<T>() {
            @Override
            public boolean test(T t) {
                return Predicate2.this.test(t) || other.test(t);
            }
        };
    }

    public static void main(String... args) {

    }
}
