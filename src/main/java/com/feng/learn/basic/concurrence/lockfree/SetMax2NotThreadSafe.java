package com.feng.learn.basic.concurrence.lockfree;


import com.feng.learn.basic.thread.annotation.NotThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;


@NotThreadSafe
public class SetMax2NotThreadSafe {
	
	private AtomicInteger max=new AtomicInteger();
	
	public void setMax(int max){
		int current=this.max.get();
		if (max>current){
			this.max.compareAndSet(current, max);
		}
	}

}
