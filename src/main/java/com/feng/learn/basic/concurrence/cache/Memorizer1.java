package com.feng.learn.basic.concurrence.cache;


import com.feng.learn.basic.thread.annotation.GuardedBy;

import java.util.HashMap;
import java.util.Map;

public class Memorizer1<A,R> implements Computable<A,R> {
	
	@GuardedBy("this")
	private final Map<A,R> cache=new HashMap<A,R>();
	private final Computable<A,R> c;
	
	public Memorizer1(Computable<A,R> c){
		this.c=c;
	}

	
	public synchronized R compute(A args) throws Exception {
		R result=cache.get(args);
		if (result==null){
			System.out.println("计算中。。 ： "+args);
			result=c.compute(args);
			cache.put(args, result);
		} else {
			System.out.println("缓存中取。。");
		}
		return result;
	}

}
