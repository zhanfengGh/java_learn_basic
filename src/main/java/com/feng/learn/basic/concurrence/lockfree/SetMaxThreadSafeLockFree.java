package com.feng.learn.basic.concurrence.lockfree;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;


@ThreadSafe
public class SetMaxThreadSafeLockFree {
	
	private AtomicInteger max=new AtomicInteger();
	
	/** Lock-Free algorithm */
	public void setMax(int max){
		for (;;){
			int current=this.max.get();
			if (max>current){
				if (this.max.compareAndSet(current, max)){
					break;
				} else {
					continue;
				}
			} else {
				break;
			}
		}
	}

}
