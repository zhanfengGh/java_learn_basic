package com.feng.learn.basic.concurrence.shutdown;

public interface LogServiceI {
	
	void log(String message) throws InterruptedException;

}
