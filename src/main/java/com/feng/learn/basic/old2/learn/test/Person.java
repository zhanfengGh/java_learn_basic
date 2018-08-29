/**
 * 
 */
package com.feng.learn.basic.old2.learn.test;
/**  
 * @author zhangzhanfeng 
 * @date Aug 29, 2017   
 */	
public class Person extends Animal {
	static {
		System.out.println("Person static");
	}
	private int legNum = 2;
	public int getLegNum() {
		return legNum;
	}
	{
		System.out.println("Person");
	}
	public static void main(String[] args) {
		Person p1 = new Person();
		Animal p = new Person();
		System.out.println(p.legNum);
		System.out.println(p.getLegNum());
	}
}

class Animal {
	static {
		System.out.println("Animal static");
	}
	int legNum = 4;
	public int getLegNum() {
		return legNum;
	}
	{
		System.out.println("Animal");
	}
}
