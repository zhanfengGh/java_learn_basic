/**
 * 
 */
package com.feng.learn.basic.old2.learn.concurrent;

import java.util.concurrent.*;


/**  
 * @author zhangzhanfeng 
 * @date Apr 22, 2017   
 */
public class ExecutorTest {
	
	private static final CountDownLatch latch = new CountDownLatch(1);
	
	static Runnable task = new Runnable() {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName());
			System.currentTimeMillis();
			try {
				TimeUnit.SECONDS.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.interrupted());
			latch.countDown();
		}
		
	};
	
	static final Callable<Integer> callable = new Callable<Integer>() {

		@Override
		public Integer call() throws Exception {
			TimeUnit.SECONDS.sleep(10);
			return 5;
		}
		
	};
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		//executor.execute(task);
		
		//executor.shutdown();
		
		
		Future<Integer> f = executor.submit(callable);
		try {
			Integer i = f.get();
			System.out.println(i);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		Class<Void> void1 = Void.class;
	}
}
