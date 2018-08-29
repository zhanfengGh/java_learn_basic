package com.feng.learn.basic.concurrence.notsafepublish;


import com.feng.learn.basic.thread.annotation.NotThreadSafe;

class Person{
	private String name;
	private int age;
	public Person(){
		
	}
	
	public Person(String name, int age){
		this.name=name;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.age=age;
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


	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}

@NotThreadSafe
public class Singleton {
	
	private static Person person;
	
	public static Person getInstance(){
		if (person==null){
			person=new Person("feng",26);
		}
		System.out.println(person);
		return person;
	}

	public static void main(String[] args) {
		Person instance = Singleton.getInstance();
		new Thread() {
			@Override
			public void run() {
				System.out.println("another: " + instance);
			}
		}.start();
	}

}
