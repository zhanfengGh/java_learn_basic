package com.feng.learn.basic.old;

class Fruit {
    private String color = "Fruit color";

    public void info() {
        System.out.println("fruit.info()...");
    }

    public Fruit getThis() {
        System.out.println(this);
        return this;
    }
}

public class Apple extends Fruit {
    private String color = "Apple color";

    public static void main(String[] args) {
        Apple a = new Apple();
        Fruit f = a.getSuper();
        System.out.println(a);
        System.out.println(f);
        System.out.println("a==f: " + (a == f));
        System.out.println("a.color: " + a.color);
        //System.out.println("f.color:" + f.color);

        a.info();
        f.info();
        a.superColor();
        a.superInfo();
    }

    @Override
    public void info() {
        System.out.println("apple.info()..");
        //super.info();
    }

    public Fruit getSuper() {
        return super.getThis();
    }

    public void superColor() {
        //System.out.println(super.color);
    }

    public void superInfo() {
        super.info();
    }


}
