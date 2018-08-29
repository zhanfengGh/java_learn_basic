package com.feng.learn.basic.concurrence.threadpool;

import java.util.concurrent.atomic.AtomicInteger;


public class Test {
	
	public static void main(String[] args){
		final String myName="feng";
		final AtomicInteger count=new AtomicInteger(0);
		Runnable task=new Runnable(){

			public void run() {
				Test.test(myName);
				int temp=count.getAndAdd(1);
				if (temp%2==0){
					Runnable task =new Runnable(){

						public void run() {
							System.out.println("Hello world!");
						}
						
					};
					SystemThreadPool.submit(task);
				}
			}
			
		};
		for (int i=0;i<3;i++)
			SystemThreadPool.submit(task);
		System.out.println("");
	}

	private static void test(String name){
		System.out.println("Hello, "+name);
		
	}
}
