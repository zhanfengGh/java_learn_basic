package com.feng.learn.basic.concurrence.status;

import java.util.concurrent.CountDownLatch;

public class Test {
	
	public static void main(String[] args){
		final CountDownLatch countDown=new CountDownLatch(1);
		Thread thread=new Thread("Thread status"){

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					System.out.println(Thread.interrupted());
					//countDown.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		};
		//thread.setDaemon(true);
		thread.start();


		try {
			Thread.sleep(1000);
			System.out.println(thread.isAlive());
			//thread.join();
			
			//countDown.await();
			System.out.println(thread.isAlive());
			System.out.println(thread.isDaemon());
			thread.interrupt();
			System.out.println(thread.isInterrupted());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//thread.start();
	}

}
