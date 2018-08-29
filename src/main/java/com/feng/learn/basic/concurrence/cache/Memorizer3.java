package com.feng.learn.basic.concurrence.cache;

import java.util.Map;
import java.util.concurrent.*;


public class Memorizer3<A,R> implements Computable<A,R> {
	
	private final Map<A,Future<R>> cache=new ConcurrentHashMap<A,Future<R>>();
	private final Computable<A,R> c;
	
	public Memorizer3(Computable<A,R> c){
		this.c=c;
	}

	public R compute(final A args) throws InterruptedException, ExecutionException {
		Future<R> result=cache.get(args);
		if (result==null){
			
			FutureTask<R> ft=new FutureTask<R>(new Callable<R>(){

				public R call() throws Exception {
					System.out.println(args+" calculate ..."+Thread.currentThread());
					return c.compute(args);
				}
				
			});
			cache.put(args, ft);
			result=ft;
			ft.run();
		} else {
			System.out.println(args+" from cache");
		}
		return result.get();
	}

}
