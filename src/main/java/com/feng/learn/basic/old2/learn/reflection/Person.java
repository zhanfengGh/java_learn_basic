package com.feng.learn.basic.old2.learn.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person {
	private String name;
	private String sex;
	private int age;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public Person() {
	};

	/**
	 * @param name2
	 * @param age
	 */
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public static Person createPerson(String name, int age) {
		return new Person(name, age);
	}

	static {
		System.out.println("Person.class init..");
	}

	{
		System.out.println("Person instance init..");
	}

	/**
	 * return the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * name the name to set
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * return the sex
	 * 
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * sex the sex to set
	 * 
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * return the age
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * the age to set
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", age=" + age + ", logger=" + logger + "]";
	}

}
