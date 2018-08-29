package com.feng.learn.basic.old.reflection.reflection2;

import com.feng.learn.basic.old.reflection.reflection2.model.Living;
import com.feng.learn.basic.old.reflection.reflection2.model.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Test {
	
	public static void line(){
		System.out.println("*****************************");
	}
	
	public static void classInfo(Class<?> clazz){
		Constructor<?>[] cons=clazz.getConstructors(); //Class<Student> 没有被初始化
		for (Constructor<?> c:cons){
			System.out.println(c);
		}
		line();
		
		Method[] methods=clazz.getDeclaredMethods(); //Class<Student> 没有被初始化
		for (Method m:methods){
			System.out.println(m);
		}
		line();
		
		Field[] fields=clazz.getDeclaredFields();
		for (Field f:fields){
			System.out.println(f);
		}
		
	}
	
	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException{
		Class<Student> clazz_stu1=Student.class;
		System.out.println(clazz_stu1);
		line();
		
		classInfo(clazz_stu1);
		line();
		
		Constructor<?> c=clazz_stu1.getConstructor();
		System.out.println(c);
		line();
		c=clazz_stu1.getConstructor(Integer.class);
		System.out.println(c);
		Object o=c.newInstance(616);
		line();
		
		Method m=clazz_stu1.getDeclaredMethod("getStudentNo");
		System.out.println(m.invoke(o));
		line();
		
		Field f=clazz_stu1.getDeclaredField("studentNo");
		f.setAccessible(true);
		System.out.println(f.get(o));
		line();
		
		Class<Living> clazz_living=Living.class;
		Method m2=clazz_living.getDeclaredMethod("isLive");
		System.out.println(m2.invoke(o));
		
		Method m3=clazz_living.getDeclaredMethod("setLive", Boolean.class);
		m3.invoke(o, false);
		System.out.println(((Student)o).isLive());
		line();
		
		Class<?> clazz_run=new Student().getClass();
		classInfo(clazz_run);
		line();
		
	}

}
