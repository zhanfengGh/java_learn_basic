package com.feng.learn.basic.old;

class Base1{
	String name="b.name";
	int age=11;
	/*
	@Override
	public String toString(){
		super.toString();
		System.out.println(this.age);
		return null;
	}
	*/
	public void print(){
		System.out.println(this);
		System.out.println(this.age);
	}
}
class Mid extends Base1{
	String name="m.name";
	private int age=22;
	/*
	@Override
	public String toString(){
		super.toString();
		System.out.println(this.age);
		return null;
	}
	*/
	@Override
	public void print() {
		super.print();
		System.out.println(this);
		System.out.println(this.age);
	}
	
	
}

class Divided1 extends Mid{
	private String name="d.name";
	int age=33;
	/*
	@Override
	public String toString(){
		super.toString();
		//System.out.println(this);
		System.out.println(this.age);
		return null;
	}
	*/
	@Override
	public void print() {
		super.print();
		System.out.println(this);
		System.out.println(this.age);
	}
	
	
}

public class FieldTest {
	
	public static void main(String[] args){
		Mid d=new Divided1();
		System.out.println(d.name);
		//System.out.println(d.age);
		System.out.println(d);
		//d.toString();
		d.print();
	}

}
