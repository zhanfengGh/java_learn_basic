package com.feng.learn.basic.old.reflection.reflection2.model;

public class Living {
	
	static {
		System.out.println("Class<Living> init.. start");
		System.out.println("Class<Living> init.. end");
	}
	
	
	{
		System.out.println("instanceof Living init.. start");
		isLive=true;
		System.out.println("instanceof Living  init.. end");
	}
	
	private boolean isLive;

	public boolean isLive() {
		return isLive;
	}

	public void setLive(Boolean isLive) {
		this.isLive = isLive;
	}
	
	
}
