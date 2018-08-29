package com.feng.learn.basic.concurrence.cache;

public class ComputableImpl implements Computable<String,Integer>{

	public Integer compute(String args) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return args.hashCode();
	}

}
