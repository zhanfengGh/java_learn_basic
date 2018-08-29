package com.feng.learn.basic.old;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSizeTest {

	public static void main(String[] args) {
		List<String> list=new ArrayList<String>(3);
		for (String s:list){
			System.out.println(s);
		}
	}
}
