package com.feng.learn.basic.concurrence;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
	
	public long timeTasks(int nThreads,final Runnable tast) throws InterruptedException{
		
		final CountDownLatch startGate=new CountDownLatch(1);
		final CountDownLatch endGate=new CountDownLatch(nThreads);
		
		for (int i=0;i<nThreads;i++){
			Thread thread=new Thread(){

				@Override
				public void run() {
					try {
						startGate.await();
						try {
							tast.run();
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			};
			thread.start();
		}
		
		long startTime=System.currentTimeMillis();
		startGate.countDown();
		endGate.await();
		long endTime=System.currentTimeMillis();
		
		return endTime-startTime;
	}

}
