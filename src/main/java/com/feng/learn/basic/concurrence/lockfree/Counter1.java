package com.feng.learn.basic.concurrence.lockfree;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class Counter1 {
	
	private AtomicInteger count=new AtomicInteger(0);
	
	public void increment(){
		count.getAndIncrement();
	}
	
	public int getCounter(){
		return count.get();
	}

}
