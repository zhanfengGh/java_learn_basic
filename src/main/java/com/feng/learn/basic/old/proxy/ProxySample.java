package com.feng.learn.basic.old.proxy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
/**
 * java动态代理实现原理, 必须面向接口 。
 * @author feng_Pc
 *
 */
interface InvocationHandler {
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}

class Meeting{
	
}

interface MeetingBaseService{
	
	Meeting loadMeeting(long id);
}

interface MeetingService extends MeetingBaseService {
	
	String getMeetingNameByMeetingId(long id);
	
	void updateMeeting(Meeting meeting);
	
}

class MeetingServiceImpl implements MeetingService{

	@Override
	public String getMeetingNameByMeetingId(long id) {
		System.out.println("getMeetingNameByMeetingId(long id) execute..");
		return "zhangfeng de  meeeting";
	}

	@Override
	public void updateMeeting(Meeting meeting) {
		System.out.println("updateMeeting(Meeting meeting) execute..");
	}

	@Override
	public Meeting loadMeeting(long id) {
		System.out.println("loadMeeting(long id) execute..");
		return null;
	}
	
}

class MeetingServiceInvocationHandler implements java.lang.reflect.InvocationHandler{

	private Object target;
	
	public MeetingServiceInvocationHandler(Object target) {
		this.target=target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		System.out.println("***********************");
		ProxySample.printClazzInfo(proxy.getClass());
		System.out.println("***********************");
		
		Object result;
		
		//前置通知
		LogUtil.logBeforMethod(args);
		
		result=method.invoke(target, args);
		
		//后置通知
		LogUtil.logAfterMethod(result);
		
		return result;
	}
	
}

class LogUtil{
	public static void logBeforMethod(Object[] args){
		System.out.println("LogUtil.logBeforMethod()："+(args==null?"null":Arrays.toString(args)));
	}
	public static void logAfterMethod(Object result){
		System.out.println("LogUtil.logAfterMethod()："+(result==null?"null":result));
	}
}

public class ProxySample{
	
	public static void printClazzInfo(Class<?> clazz){
		
		System.out.println(clazz);
		System.out.println("Field: ");
		Field[] fields=clazz.getDeclaredFields();
		for (Field f:fields){
			System.out.println(f);
		}
		
		System.out.println("Method: ");
		Method[] methods=clazz.getDeclaredMethods();
		for (Method	m:methods){
			System.out.println(m);
		}
	}
	
	@SuppressWarnings("restriction")
	private static void writeToFile() throws IOException{
		byte[] datas=sun.misc.ProxyGenerator.generateProxyClass("ProxyObject", new Class[] {MeetingService.class});
		FileOutputStream file=new FileOutputStream("E:/ProxyObject.class");
		file.write(datas);
		file.close();
	}
	
	public static void main(String[] args) throws IOException{
		MeetingService service=new MeetingServiceImpl();
		MeetingServiceInvocationHandler handler=new MeetingServiceInvocationHandler(service);
		Class<? extends MeetingService> clazz=service.getClass();
		for (Class<?> c:clazz.getInterfaces()){
			System.out.println("interface: "+c);
		}
		
		MeetingService proxy=(MeetingService) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
		
		writeToFile();
		
		proxy.getMeetingNameByMeetingId(1000L);
		
		proxy.updateMeeting(new Meeting());
		
		proxy.loadMeeting(999l);
		
		Class<?> proxyClazz=proxy.getClass();
		System.out.println("000:"+proxyClazz);
		
		printClazzInfo(proxyClazz);
	}
	
}
