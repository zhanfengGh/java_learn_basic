package com.feng.learn.basic.concurrence.atomic.lockfree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Test {
	
	
	
	public static void main(String[] args){
		AtomicReference<String> test=new AtomicReference<String>();
		System.out.println(test.get());
		List<Integer> datas=new ArrayList<Integer>();
		datas.add(1);
		datas.add(1);
		for(int i=0;i<10;i++){
			datas.add(i);
		}
		Iterator<Integer> iterator=datas.iterator();
		System.out.println(datas);
		/*int data;
		while (iterator.hasNext()){
			data=iterator.next();
			if (data%2!=0){
				iterator.remove();
			}
		}*/
		System.out.println(datas);
		
		int a=5;
		Integer b=5;
		System.out.println(System.identityHashCode(b));
		System.out.println(System.identityHashCode(a));
	}

}
