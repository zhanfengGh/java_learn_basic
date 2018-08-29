package com.feng.learn.basic.concurrence.notsafepublish;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	
	public static void main(String[] args){
		ExecutorService executor=Executors.newCachedThreadPool();
		final CountDownLatch startGate=new CountDownLatch(1);
		Runnable tast=new Runnable(){

			public void run() {
				try {
					startGate.await();
					Person singleton=SingletonLazyInit2.getInstance();
					System.out.println(singleton.getAge());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		for (int i=0;i<50;i++){
			executor.submit(tast);
		}
		startGate.countDown();
	}

}
