/**
 * 
 */
package com.feng.learn.basic.old2.learn.init;

/**
 * @author feng
 *
 */
public class Person {
	static {
		System.out.println("Person.static block");
	}

	{
		System.out.println("Person block");
	}
}
