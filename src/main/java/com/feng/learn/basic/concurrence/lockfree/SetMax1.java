package com.feng.learn.basic.concurrence.lockfree;


import com.feng.learn.basic.thread.annotation.GuardedBy;
import com.feng.learn.basic.thread.annotation.ThreadSafe;

@ThreadSafe(authors = {"zhanfeng.zhang"})
public class SetMax1 {
	
	@GuardedBy("this")
	private int max;
	
	public synchronized void setMax(int max){
		if (max>this.max){
			this.max=max;
		}
	}

}
