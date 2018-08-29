package com.feng.learn.basic.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskUtils {
	
	private static ExecutorService fixedPool=Executors.newFixedThreadPool(4);
	private static ExecutorService cachedPool=Executors.newCachedThreadPool();
	
	public static void submit2FixedPool(Runnable task){
		fixedPool.submit(task);
	}
	
	public static void submit2CachedPool(Runnable task){
		cachedPool.submit(task);
	}

}
