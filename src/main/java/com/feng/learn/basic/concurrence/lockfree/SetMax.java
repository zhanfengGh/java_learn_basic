package com.feng.learn.basic.concurrence.lockfree;


import com.feng.learn.basic.thread.annotation.NotThreadSafe;

@NotThreadSafe
public class SetMax {
	
	private int max;
	
	public void setMax(int max){
		if (max>this.max){
			this.max=max;
		}
	}

}
