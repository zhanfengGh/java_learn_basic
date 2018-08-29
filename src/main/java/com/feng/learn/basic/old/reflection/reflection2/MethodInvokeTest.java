package com.feng.learn.basic.old.reflection.reflection2;

import com.feng.learn.basic.old.reflection.reflection2.model.Person;
import com.feng.learn.basic.old.reflection.reflection2.model.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MethodInvokeTest {

	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Person p=new Student();
		
		p.setName("feng");
		System.out.println(p.getName());
		
		Class<?> clazz1=p.getClass();
		System.out.println(clazz1);
		
		//Method m=clazz1.getDeclaredMethod("getName"); //throw NoSuchMethodException
		Method m=MethodInvoke.tryToGetMethod(clazz1, "getName", null);
		System.out.println(m);
		
		//MethodInvoke.printAllFieldsAndMethodsAndConstructor(clazz1);
		
		MethodInvoke.runMethod(p, "setStudentNo", true, 20070001);
		
		m=MethodInvoke.tryToGetMethod(clazz1, "getStudentNo", null);
		System.out.println(m.invoke(p));
		
	}
}
