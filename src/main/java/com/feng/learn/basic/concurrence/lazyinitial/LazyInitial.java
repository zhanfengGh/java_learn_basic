package com.feng.learn.basic.concurrence.lazyinitial;


import com.feng.learn.basic.thread.annotation.NotThreadSafe;

@NotThreadSafe
public class LazyInitial {
	
	private static ExpensiveObject instance;
    
	public static ExpensiveObject getInstance() throws InterruptedException{
		if (instance==null){
			instance=new ExpensiveObject();
		}
		return instance;
	}
	
}