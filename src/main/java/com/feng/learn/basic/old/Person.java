package com.feng.learn.basic.old;

public class Person {
	
	private static String skinColor;
	
	static{
		System.out.println("Class Person init...");
	}
	
	private int age;
	private String name;
	
	{
		this.name="b1";
		this.height=178;
		System.out.println(this.name);
	}
	
	private double height=175;
	
	public Person(){
		this.name="c";
		System.out.println(this.name);
		System.out.println("Person()...");
	}
	{
		this.name="b2";
		System.out.println(this.name);
	}
	
	public Person(String name,int age){
		System.out.println("Person(String,int)...");
		this.name=name;
		this.age=age;
	}
	
	public static void main(String[] args){
		new Person("feng",18);
	}

	public int getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getSkinColor() {
		return skinColor;
	}

	public static void setSkinColor(String skinColor) {
		Person.skinColor = skinColor;
	}

	
}
