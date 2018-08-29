package com.feng.learn.basic.concurrence.lazyinitial;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

@ThreadSafe
public class LazyInitial2 {
	
	private static ExpensiveObject instance;
	
	static{
		try {
			instance=new ExpensiveObject();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    
	public static ExpensiveObject getInstance() throws InterruptedException{
		return instance;
	}
	
}