/**
 * 
 */
package com.feng.learn.basic.old2.learn.serialization;

import java.io.Serializable;

/**
 * @author feng
 *
 */
public class Person11 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6342379361761518761L;

	static {
		System.out.println("Person static block");
	}

	{
		System.out.println("Person block");
	}

	public Person11() {
		System.out.println("Person constructor");
	}

	public Person11(String name, int age) {
		this.name = name;
		this.age = age;
	}

	private String name;
	private int age;

	// private Integer age2;
	/**
	 * return the age
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * the age to set
	 * 
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
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
	 * the name to set
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
