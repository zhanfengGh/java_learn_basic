/**
 * 
 */
package com.feng.learn.basic.old2.learn.spring.model;

/**
 * @author feng
 *
 */
public class PersonFactory {

	public static Person createPerson() {
		return new Person();
	}

	public static Person createPerson(String name, int age) {
		return new Person(name, age);
	}

	public Person getPerson() {
		return new Person();
	}

	public Person getPerson(String name, int age) {
		return new Person(name, age);
	}
}
