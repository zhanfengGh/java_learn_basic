package com.feng.learn.basic.concurrence;


import com.feng.learn.basic.thread.annotation.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {
	
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

}
