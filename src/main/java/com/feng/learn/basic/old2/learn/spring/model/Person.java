/**
 * 
 */
package com.feng.learn.basic.old2.learn.spring.model;

import java.util.Properties;

/**
 * @author feng
 *
 */
public class Person {
	private String name;
	private int age;
	private Properties properties;
	private Address addr;
	private Address home;
	private Address work;

	Person() {
		System.out.println("Person()");
	}

	public Person(String name, int age) {
		System.out.println("Person(String, int)");
		this.name = name;
		this.age = age;
	}

	public Person(String name, int age, Address addr) {
		this(name, age);
		System.out.println("Person(String, int, Address)");
		this.addr = addr;
	}

	/*
	public void init() {
		System.out.println("Person.this.init()");
	}
	*/

	public void initMethod() {
		System.out.println("Person.this.initMethod()");
	}

	/*
	public void destroy() {
		System.out.println("Person.this.destroy()");
	}
	*/

	public void destroyMethod() {
		System.out.println("Person.this.destroyMethod()");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", properties=" + properties + ", addr=" + addr + ", home="
				+ home + ", work=" + work + "]";
	}

	/**
	 * return the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * the name to set
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * return the properties
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * the properties to set
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * return the addr
	 * @return the addr
	 */
	public Address getAddr() {
		return addr;
	}

	/**
	 * the addr to set
	 * @param addr the addr to set
	 */
	public void setAddr(Address addr) {
		this.addr = addr;
	}

	/**
	 * return the home
	 * @return the home
	 */
	public Address getHome() {
		return home;
	}

	/**
	 * the home to set
	 * @param home the home to set
	 */
	public void setHome(Address home) {
		this.home = home;
	}

	/**
	 * return the work
	 * @return the work
	 */
	public Address getWork() {
		return work;
	}

	/**
	 * the work to set
	 * @param work the work to set
	 */
	public void setWork(Address work) {
		this.work = work;
	}

}
