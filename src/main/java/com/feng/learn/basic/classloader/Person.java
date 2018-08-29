package com.feng.learn.basic.classloader;

public class Person {
	public static int eyeNum;
	public String name;
	public static final String time ;
	private static Children childs;
	static{
		System.out.println(eyeNum);
		System.out.println("Class Person init..");
        time=System.currentTimeMillis()+"";
        System.out.println(time);

		eyeNum=2;
	}

	{
		System.out.println("Object Person init..");
	}

	public Person(Children childs){
		this();
		Person.childs=childs;

	}

	public Person(){
		System.out.println("Person() init..");
	}
	Person(int eyeNum){
		this.eyeNum=eyeNum;
	}
	private Person(String name){
		this();
		System.out.println("Person(String) init..");
		this.name=name;
	}

	public static void setEyeNum(Integer eyeNum){
		Person.eyeNum=eyeNum;
	}

	public void setName(String name){
		this.name=name;
	}

	public static void main(String[] args){
		System.out.println("Person.main()...");
		new Person();
		System.out.println("********");
		new Person("feng");
	}


}
