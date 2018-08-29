package com.feng.learn.basic.nestclass;

public class Class1 {
    protected InnerClass1 ic;

    public Class1() {
        ic = this.new InnerClass1();
    }

    static public void main(String[] args) {
        Class1 c1 = new Class1();
        c1.displayStrings();
    }

    public void displayStrings() {
        System.out.println(ic.getString() + ".");
        System.out.println(ic.getAnotherString() + ".");
    }

    protected class InnerClass1 {

        public String getString() {
            return "InnerClass1: getString invoked";
        }

        public String getAnotherString() {
            return "InnerClass1: getAnotherString invoked";
        }
    }
}
