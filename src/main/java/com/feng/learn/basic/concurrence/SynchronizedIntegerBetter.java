package com.feng.learn.basic.concurrence;


import com.feng.learn.basic.thread.annotation.GuardedBy;
import com.feng.learn.basic.thread.annotation.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class SynchronizedIntegerBetter {

	Lock numberLock=new ReentrantLock();
	@GuardedBy("numberLock")
	private int number;

	public int getNumber() {
		numberLock.lock();
		try {
			return number;
		} finally{
			numberLock.unlock();
		}
	}

	public void setNumber(int number) {
		numberLock.lock();
		try {
			this.number = number;
		} finally{
			numberLock.unlock();
		}
	}
	
	
}
