/**
 * 
 */
package com.feng.learn.basic.old2.learn.singleton;


import com.feng.learn.basic.thread.annotation.NotThreadSafe;
import com.feng.learn.basic.thread.annotation.ThreadSafe;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author feng
 *
 */
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -531956225924260285L;

	private static class InstanceHolder {
		private static final Person instance = new Person("zhangfeng", 26);
	}

	private Person() {
		System.out.println("Person()");
	}

	private Person(String name, int age) {
		System.out.println("Person(name:String, age:int)");
		this.name = name;
		this.age = age;
	}

	public static final Person getInstance() {
		return InstanceHolder.instance;
	}

	private String name;
	private int age;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}

@NotThreadSafe
class Singleton {
	private static Singleton instance;

	private Singleton() {
	};

	/** 延迟初始化 */
	public static Singleton getInstance() {
		if (null == instance) {
			instance = new Singleton();
		}
		return instance;
	}

}

@ThreadSafe(authors = { "zhangzhanfeng" })
class Singleton2 {
	private static final Singleton2 instance = new Singleton2();

	private Singleton2() {
	};

	public static Singleton2 getInstance() {
		return instance;
	}
}

@ThreadSafe(authors = { "zhangzhanfeng" })
class Singleton3 {
	private static class InstanceHolder {
		private static final Singleton3 instance = new Singleton3();
	}

	private Singleton3() {
	}

	public static Singleton3 getInstance() {
		return InstanceHolder.instance;
	}
}

@ThreadSafe(authors = { "zhangzhanfeng" })
class Singleton4 {

	private static final Object NULL = new Object();
	private static final AtomicReference<Object> instanceHolder = new AtomicReference<Object>(NULL);

	private Singleton4() {
	}

	public static Singleton4 getInstance() {
		Object instance = instanceHolder.get();
		if (NULL == instance) {
		    instance = new Singleton4();
			if (!instanceHolder.compareAndSet(NULL, instance)) {
				instance = instanceHolder.get();
			}
		}
		return (Singleton4) instance;
	}
}
