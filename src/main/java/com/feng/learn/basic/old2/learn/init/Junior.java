/**
 * 
 */
package com.feng.learn.basic.old2.learn.init;

/**
 * @author feng
 *
 */
public class Junior extends Student {
	static {
		System.out.println("Junior.static block");
	}

	{
		System.out.println("Junior block");
	}
}
