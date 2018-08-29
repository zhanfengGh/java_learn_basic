package com.feng.learn.basic.thread.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**  
 * @author zhangzhanfeng 
 * @date Dec 5, 2017   
 */
public class LockTest {

	@SuppressWarnings("unused")
	private static long count = 0;
	private static AtomicLong count2 = new AtomicLong(0);

	public static void main(String[] args) {
		final Object lock = new Object();
		Runnable task = new Runnable() {

			@Override
			public void run() {
				while (true) {
					synchronized (lock) {
						LockTest.count++;
					}
				}
			}

		};
		
		task = new Runnable() {

			@Override
			public void run() {
				while (true) {
					count2.getAndIncrement();
				}
			}
			
		};

		for (int i = 0; i < 16; i++) {
			new Thread(task, "Thread-" + i).start();
		}
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				synchronized (lock) {
					System.out.println(LockTest.count2.get());
				}
			}
			
		}, 1, 1, TimeUnit.SECONDS);
	}
}
