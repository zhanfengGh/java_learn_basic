/**
 * 
 */
package com.feng.learn.basic.old2.learn.concurrent;

import java.util.concurrent.*;

/**  
 * @author zhangzhanfeng 
 * @date Sep 22, 2017   
 */
public class Main {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		final String result = "5";
		Future<String> f = executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("execute");
			}
			
		}, result);
		
		String r = f.get();
		System.out.println(r);
		
		Future<String> f2 = executor.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "tttt";
			}
			
		});
	}
}
