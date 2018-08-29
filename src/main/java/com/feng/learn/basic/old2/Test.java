package com.feng.learn.basic.old2;

import java.util.*;

/**
 * 
 */

/**
 * @author zhangzhanfeng 
 * @date Aug 29, 2017   
 */
public class Test {
	
	public static void  main(String args[]) {
		int aa = 0;
		aa("ssss", "String2", "dddd", aa);
		
	}
	public static void aa(Object... args) {
		System.out.println(args[1]);
		System.out.println("asfd");
	}
	
	public static void main5(String[] args) {
		List<Integer> array = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			array.add(i);
		}
		Iterator<Integer> it = array.iterator();
		int i = 0;
		while (it.hasNext()) {
			it.next();
			if (i++ % 2 == 0) {
				it.remove();
			}
			System.out.println(array);
		}
	}
	
	public static void main4(String args[]) {
		short s = 5;
		short s2 = 6;
		//s = s + s2;
		//s = s + 5;
		s += 5;
	}
	
	
	public static void main3(String[] args) {
		Map<Byte,String> map = new HashMap<Byte, String>();
		byte b = 1;
		map.put(b, "Hello,World");
		System.out.println(map.get(1)); // ?
	}
	
	public static void main2(String[] args) {
		List<Person> list = new ArrayList<Person>(1);
		list.add(new Person());
		list.add(new Person());
		List<Person> list2 = new LinkedList<Person>();
		list2.add(new Person());
		list2.add(new Person());
	}
	public static void main1(String[] args) {
		int[] array=new int[5];
		array[4]=5;
		Integer[] array2=new Integer[5];
		array2[4]=5; // 自动装箱？
		//array=array2;
	}
}
