package com.feng.learn.basic.concurrence.notsafepublish;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

@ThreadSafe
/**
 * 
 * @author feng_Pc
 * 
 * lazyInitial and ThreadSafe
 * 注意Person的构造时机。。
 * 
 */
public class SingletonLazyInit2 {
	
	private static class ObjectHolder{
		private static Person singleton=new Person("feng",26);
	}
	
	public static Person getInstance(){
		Person singleton= ObjectHolder.singleton;
		System.out.println(singleton);
		return singleton;
	}

}
