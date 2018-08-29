package com.feng.learn.basic.concurrence.longOrdouble;


import com.feng.learn.basic.thread.annotation.NotThreadSafe;

@NotThreadSafe
public class Long64NotThreadSafe {
	
	private long data;

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}
	
	

}
