package com.feng.learn.basic.concurrence.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memorizer2<A,R> implements Computable<A,R> {
	
	private final Map<A,R> cache=new ConcurrentHashMap<A,R>();
	private final Computable<A,R> c;
	
	public Memorizer2(Computable<A,R> c){
		this.c=c;
	}

	
	public R compute(A args) throws Exception {
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
