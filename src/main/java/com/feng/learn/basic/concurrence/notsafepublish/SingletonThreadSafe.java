package com.feng.learn.basic.concurrence.notsafepublish;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

@ThreadSafe
/**
 * 
 * @author feng_Pc
 *
 *	第一次调用getInstance()时会触发SingletonThreadSafe类的初始化。
 */
public class SingletonThreadSafe {

	private static Person person=new Person("feng", 26);

	public static Person getInstance() {
		System.out.println(person);
		return person;
	}

}
