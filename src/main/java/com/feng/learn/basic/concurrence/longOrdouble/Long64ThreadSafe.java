package com.feng.learn.basic.concurrence.longOrdouble;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

@ThreadSafe
public class Long64ThreadSafe {
	
	private volatile long data;

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}
	
	

}
