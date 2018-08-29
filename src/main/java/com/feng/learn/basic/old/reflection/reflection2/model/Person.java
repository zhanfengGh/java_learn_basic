package com.feng.learn.basic.old.reflection.reflection2.model;

public class Person extends Living {
	
	static {
		System.out.println("Class<Person> init.. start");
		setLegNumber(2);
		System.out.println("Class<Person> init.. end");
	}
	
	{
		System.out.println("instanceof Person init.. start");
		System.out.println("instanceof Person  init.. end");
	}
	
	private static int legNumber;
	
	private String name;
	private int age;
	
	public Person(){}
	
	public Person(String name){
		this(name, 0);
	}
	
	public Person(String name, int age){
		this.name=name;
		this.age=age;
	}
	

	public static int getLegNumber() {
		return legNumber;
	}

	public static void setLegNumber(int legNumber) {
		Person.legNumber = legNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
