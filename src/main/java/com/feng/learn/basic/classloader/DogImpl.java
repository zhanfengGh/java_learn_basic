package com.feng.learn.basic.classloader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

interface Dog {
	void info(String name);
	void run();

}

class DogUtil{
	public void method1(){
		System.out.println("dogUtil.method1()..");
	}
	
	public void method2(){
		System.out.println("dogUtil.method2()...");
	}
}

class  DogInvocationHandler implements InvocationHandler{
	
	private Object target;
	
	//public DogInvocationHandler(){};
	
	public DogInvocationHandler(Object target){
		this.target=target;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Method: "+method);
		if (args!=null)
			System.out.println("args: "+Arrays.toString(args));
		DogUtil du=new DogUtil();
		du.method1();
		Object result = method.invoke(target, args);
		du.method2();
		return result;
	}
	
}


public class DogImpl implements Dog {

	public void info(String name) {
		System.out.println("我是一只猎狗,我的名字是："+name);
	}

	public void run() {
		System.out.println("我正在拼命的追兔子。。。");
	}
	
	public static void main(String[] args){
		Dog dog=new DogImpl();
		DogInvocationHandler handler=new DogInvocationHandler(dog);
		Dog dogProxy=(Dog) ProxyFactory.getProxy(dog, handler);
		dogProxy.info("ddddddddd");
		dogProxy.run();
		
	}
	
}

class ProxyFactory{
	public static Object getProxy(Object target,InvocationHandler handler){
		Object proxy=null;
		
		Class<?> targetClazz=target.getClass();
		proxy=Proxy.newProxyInstance(targetClazz.getClassLoader(), targetClazz.getInterfaces(), handler);
		
		return proxy;
	}
}
