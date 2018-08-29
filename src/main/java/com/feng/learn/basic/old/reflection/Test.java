package com.feng.learn.basic.old.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.feng.learn.basic.old.Person;

public class Test {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException{
		Class<Person> clazz;
		clazz=Person.class;  //只会触发类加载，不会触发类初始化。
		/* 
		try {
			clazz=(Class<Person>) Class.forName("learn.Person");  // 会触发类的初始化
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			clazz=(Class<Person>) ClassLoader.getSystemClassLoader().loadClass("learn.Person"); //不会触发类的初始化。
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
		System.out.println("**");
		System.out.println(clazz);
		System.out.println(clazz.getClass());
		Method getAge=clazz.getDeclaredMethod("getAge");
		System.out.println("@@@");
		Person p1=new Person("feng", 18), p2=new Person("zhang", 25);
		Method getAge1=p1.getClass().getMethod("getAge"), getAge2=p2.getClass().getDeclaredMethod("getAge");
		
		System.out.println(System.identityHashCode(getAge));
		System.out.println(System.identityHashCode(getAge1));
		System.out.println(System.identityHashCode(getAge2));
		System.out.println(getAge2.equals(getAge1));
		System.out.println(getAge2==getAge1);
		System.out.println(">>>>>>>>>>>>>>>");
		
		System.out.println(getAge2.invoke(p1));
		System.out.println(getAge2.invoke(p2));
		
		System.out.println(">>>.........");
		/***
		 * 反射时基本类型和包装类型无法进行转换。。。。。。
		 */
		Method setAge=clazz.getDeclaredMethod("setAge", Integer.class);
		setAge.invoke(p1, 88);
		setAge.invoke(p2, 22);
		System.out.println(p1.getAge());
		System.out.println(p2.getAge());
		
		System.out.println("***********************");
		Field age=clazz.getDeclaredField("age"),age1=p1.getClass().getDeclaredField("age"), age2=p2.getClass().getDeclaredField("age");
		System.out.println(age2.equals(age1));
		age.setAccessible(true);
		System.out.println(age.get(p1));
		age.setInt(p1, 144);
		System.out.println(age.get(p1));
		
	}
	
}
