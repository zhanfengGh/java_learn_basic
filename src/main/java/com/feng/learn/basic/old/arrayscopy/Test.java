package com.feng.learn.basic.old.arrayscopy;

import java.util.Arrays;

public class Test {
	
	private  static <T> String arrayToString(T[] array){
		String result;
		StringBuilder sb=new StringBuilder("[");
		for (T item:array){
			sb.append(item).append(", ");
		}
		if (sb.length()!=1){
			result=sb.substring(0, sb.length()-2)+"]";
		} else{
			result=sb.append(']').toString();
		}
		return result;
	}
	
	public static void main(String[] args) {
		String[] strs={"1", "2", "3", "4", "5", "6"};
		String[] copy=new String[0];
		System.out.println(arrayToString(strs));
		System.out.println(arrayToString(copy));
		
		copy=Arrays.copyOf(strs, 6);
		System.out.println(arrayToString(copy));
		
		copy=Arrays.copyOf(strs, 10);
		System.out.println(arrayToString(copy));
		
		copy=Arrays.copyOf(strs, 2);
		strs[1]="1000";
		System.out.println(arrayToString(copy));
		System.out.println(arrayToString(strs));
	}

}
