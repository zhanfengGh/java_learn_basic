package com.feng.learn.basic.old;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class PrivateMethodInvokeTest {
	
	/**
	 * 
	 * @param clazz
	 * @param methodName
	 * @param argsType
	 * @return null（表示没有找到方法）or 找到的Method
	 */
	public static Method tryToGetMethod(Class<?> clazz, String methodName, Class<?>[] argsType){
		Method method=null;
		while (method==null && clazz!=null){
			try {
				method=clazz.getDeclaredMethod(methodName, argsType);
			} catch(NoSuchMethodException e){
			} 
			// 拿到继承的父类 这里不用管对象所实现的接口
			clazz=clazz.getSuperclass();
		}
		return method;
	}
	
	public static void runMethod(Object obj, String methodName, boolean argsOfMethodUsePrimitive, Object...args) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Method method=tryToGetMethod(obj, methodName, argsOfMethodUsePrimitive, args);
		if (method==null){
			throw new NoSuchMethodException();
		}
		if (Modifier.isPrivate(method.getModifiers())){
			try {
				method.setAccessible(true);
				method.invoke(obj, args);
			} catch(Exception e){
				throw new RuntimeException(e);
			} finally{
				method.setAccessible(false);
			}
		} else{
			method.invoke(obj, args);
		}
	}
	
	/**
	 * 尝试从obj运行时类中获取方法名为methodName和可以接受args参数的反射方法
	 * @param obj 
	 * @param methodName 方法名字
	 * @param argsOfMethodUsePrimitive true(所反射的方法的形参列表中若有8种基本类型时，他们全部都是非包装类型) or false(所反射的方法中若有8种基本类型时，他们全部都是包装类型)
	 * @param args 实参列表
	 * @return null(没有找到方法) or Method(找到的反射方法)
	 */
	private static Method tryToGetMethod(Object obj, String methodName, boolean argsOfMethodUsePrimitive, Object...args){
		Method method=null;
		Class<?> clazz=obj.getClass();
		Class<?>[] argsType=new Class<?>[args.length];
		for (int i=0;i<args.length;i++){
			Class<?> temp=args[i].getClass();
			if (argsOfMethodUsePrimitive){
				argsType[i]=changeToPrimitiveType(temp);
			} else{
				argsType[i]=temp;
			}
		}
		
		// 在类本身和所有的父类中查找方法
		method=tryToGetMethod(clazz, methodName, argsType);
		
		if (method==null && args.length>0){
			List<Class<?>[]> argsSuperTypes=new ArrayList<Class<?>[]>();
			for (Class<?> argType:argsType){
				argsSuperTypes.add(getAllSuperOfArgType(argType).toArray(new Class<?>[0]));
			}
			int[] point=new int[args.length];
			int methodFoundCount=0;
			while (point[point.length-1]<argsSuperTypes.get(args.length-1).length){
				for (int i=0;i<args.length;i++){
					argsType[i]=argsSuperTypes.get(i)[point[i]];
				} 
				method=tryToGetMethod(clazz, methodName, argsType);
				if (method !=null){
					methodFoundCount++;
				}
				
				// 驱动指针的推动
				point[0]++;
				
				for (int i=0;i<point.length-1;i++){
					if (point[i]>=argsSuperTypes.get(i).length){
						point[i]=0;
						point[i+1]++;
					}
				}
			}
			// 找到的方法大于1个时，不能确定用哪个方法，这里把method=null
			if (methodFoundCount!=1){
				method=null;
			}
		}
		return method;
	}
	
	/**
	 * 拿到一个类所实现的所有接口和继承的父类
	 * @param argType
	 * @return
	 */
	private static List<Class<?>> getAllSuperOfArgType(Class<?> argType) {
		List<Class<?>> result=new ArrayList<Class<?>>();
		while (argType!=null){
			result.add(argType);
			for (Class<?> implementedInterface:argType.getInterfaces()){
				if (!result.contains(implementedInterface)){
					result.add(implementedInterface);
				}
			}
			argType=argType.getSuperclass();
		}
		return result;
	}

	/**
	 * 把8种包装类型转换成原始类型，用以反射时匹配包含原始类型的方法
	 * @param clazz
	 * @return
	 */
	private static Class<?> changeToPrimitiveType(Class<?> clazz) {
		if (clazz==Integer.class){
			clazz=Integer.TYPE;
		} else if (clazz==Double.class){
			clazz=Double.TYPE;
		} else if (clazz==Character.class){
			clazz=Character.TYPE;
		} else if (clazz==Byte.class){
			clazz=Byte.TYPE;
		} else if (clazz==Boolean.class){
			clazz=Boolean.TYPE;
		} else if (clazz==Short.class){
			clazz=Short.TYPE;
		} else if (clazz==Long.class){
			clazz=Long.TYPE;
		} else if (clazz==Float.class){
			clazz=Float.TYPE;
		} else {
		}
		return clazz;
	}
	
	
	public static List<Class<?>> getAllSuperObject(Object obj, boolean primitive){
		List<Class<?>> all=new ArrayList<Class<?>>();
		Class<?> clazz=obj.getClass();
		if (primitive){
			clazz=changeToPrimitiveType(clazz);
		}
		while(clazz!=null){
			all.add(clazz);
			clazz=clazz.getSuperclass();
		}
		return all;
	}
}
