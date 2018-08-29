package com.feng.learn.basic.thread.test;

import lombok.extern.slf4j.Slf4j;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**  
 * @author zhangzhanfeng 
 * @date Dec 1, 2017   
 */
@Slf4j
public class TestUncaughtExceptionHandler {

	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1, new ThreadFactory() {

			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("mysql-scheduled-flush-thread");
				thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

					@Override
					public void uncaughtException(Thread t, Throwable e) {
						log.error("unexpected error.", e);
					}
					
				});
				return thread;
			}

		});
		
		executor.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				throw new RuntimeException();
			}
			
		}, 30, 30, TimeUnit.SECONDS);
	}
}
