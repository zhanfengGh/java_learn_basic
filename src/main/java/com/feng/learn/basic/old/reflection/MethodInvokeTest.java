package com.feng.learn.basic.old.reflection;


import com.feng.learn.basic.old.Person;
import com.feng.learn.basic.old.Student;

public class MethodInvokeTest {

	public static void main(String[] args) {
		Person p=new Student();
		
		p.setName("feng");
		System.out.println(p.getName());
		
		Class<?> clazz1=p.getClass();
		System.out.println(clazz1);
		
		
	}
}
