package com.feng.learn.basic.old;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PrivateMethodInvokeTest2 {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		/*
		Person p=new Student();
		Method method=PrivateMethodInvokeTest.tryToGetMethod(p, "getName");
		System.out.println(method);
		String name=(String) method.invoke(p);
		System.out.println(name);
		//////
		method=p.getClass().getDeclaredMethod("setClassNo", Integer.TYPE);
		System.out.println(method);
		//////
		
		List<Class<?>> clazzes=PrivateMethodInvokeTest.getAllSuperObject(4, false);
		System.out.println(clazzes);
		*/
		//List<Class<?>> clazzes=PrivateMethodInvokeTest.getAllOfArgType(Integer.TYPE);
		//System.out.println(clazzes);
	}
}
