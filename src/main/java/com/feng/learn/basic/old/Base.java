package com.feng.learn.basic.old;

public class Base {

    static {
        System.out.println("Base.static()..");
    }

    private final int i = 5;

    {
        System.out.println("base.block().." + this);
        System.out.println("block: " + this.i);
    }

    public Base() {

        //this.i=22;
        System.out.println("constructor: " + this.i);
        System.out.println("base().." + this);
        this.info();
    }

    public static void main(String[] args) {
        System.out.println("base.main()..");
        Divided d = new Divided();
        System.out.println("base.main().....");
        d.info();
    }

    void info() {
        System.out.println("base.info() start..");
        System.out.println("base.info().." + this);
        System.out.println("base.info(): " + i);
        System.out.println("base.info() end.");
    }

}

class Divided extends Base {

    static {
        System.out.println("Divided.static()..");
    }

    private int i;

    public Divided() {
        //super();
        System.out.println("divided.block:" + this.i);
        i = 3;
    }

    @Override
    public void info() {
        //super.info();
        System.out.println("divided.info(): " + this.i);
    }
}
