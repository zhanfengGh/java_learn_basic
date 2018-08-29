package com.feng.learn.basic.concurrence.lockfree;


import com.feng.learn.basic.thread.annotation.GuardedBy;
import com.feng.learn.basic.thread.annotation.ThreadSafe;

@ThreadSafe
public class Counter {

	@GuardedBy("this")
	private int count;
	
	public synchronized void increment(){
		count++;
	}
	public int getCount(){
		return count;
	}
}
