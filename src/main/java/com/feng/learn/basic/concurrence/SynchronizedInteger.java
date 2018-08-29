package com.feng.learn.basic.concurrence;


import com.feng.learn.basic.thread.annotation.GuardedBy;
import com.feng.learn.basic.thread.annotation.ThreadSafe;

@ThreadSafe
public class SynchronizedInteger {

	@GuardedBy("this")
	private int number;

	public synchronized int getNumber() {
		return number;
	}

	public synchronized void setNumber(int number) {
		this.number = number;
	}
	
	
}
