package com.feng.learn.basic.concurrence.lazyinitial;

public class ExpensiveObject {
	
	public ExpensiveObject() throws InterruptedException{
		System.out.println("ExpensiveObject object initial start...");
		Thread.sleep(20000);
		System.out.println("ExpensiveObject object initial end");
	}

}
