package com.feng.learn.basic.classloader;

import java.lang.reflect.InvocationTargetException;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		/*
		//Class<?> c=Class.forName("Person"); //会导致类初始化；
		Class<?> c=Person.class;
		System.out.println("9999");
		Constructor<?> con=c.getDeclaredConstructor(String.class);
		con.setAccessible(true);
		//con.newInstance("feng");
		//c.newInstance();
		*/
		
		/*
		Class<?> c=Person.class; //不会导致类初始化。
		Constructor<?>[] cons=c.getConstructors();
		for (Constructor<?> con:cons){
			System.out.println(con);
		}
		
		System.out.println(c.getConstructor(Class.forName("Children")));
		//c.getAnnotation(Deprecated.class);
		
		*/
		
		/*
		Method[] ms=c.getMethods();
		for (Method m:ms){
			System.out.println(m);
		}
		*/
		/*
		Person p=new Person();
		Method setEyeNum=c.getMethod("setEyeNum", Integer.class);
		setEyeNum.invoke(p, 5);
		System.out.println(p.eyeNum);
		Field[] fs=c.getFields();
		for (Field f:fs){
			System.out.println(f);
		}
		*/
		
		/*
		Class<?> mService=MeetingService.class;
		System.out.println("********");
		MeetingService mServiceProxy = (MeetingService) Proxy.newProxyInstance(mService.getClassLoader(), new Class<?>[]{mService},new MyInvocationHandler());
		mServiceProxy.getMeetingByMeetingId(2000L);
		mServiceProxy.updateMeeting(null);
		
		*/
		/*
		Set<Entry<Object,Object>> ps=System.getProperties().entrySet();
		for (Entry<Object,Object> e:ps){
			System.out.println(e.getKey()+"\t"+e.getValue());
		}
		*/
		/*
		ClassLoader cl=App.class.getClassLoader();
		cl.loadClass("Person");
		Class.forName("Person", true, cl);
		*/
	}
}
