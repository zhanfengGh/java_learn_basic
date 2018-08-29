package com.feng.learn.basic.concurrence.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test {
	
	public static void main(String[] args){
		new Test().performanceTest();
	}
	
	private void test() throws InterruptedException{
		//doSomething..
		Thread.sleep(20000);
	}
	
	private void collectTest(){
		//doSomething..
		System.out.println("All test passed..");
	}
	
	/**
	 * CyclicBarrier 所有的线程都到达一定条件时，继续执行。
	 * 
	 * 
	 * 
	 */
	public void performanceTest(){
		
		final CyclicBarrier barrier=new CyclicBarrier(3, new Runnable(){

			public void run() {
				collectTest();
			}
			
		});
		
		Thread thread1=new Thread("testThread1: start.."){

			@Override
			public void run() {
				try {
					System.out.println("testThread1: calculating..");
					Thread.sleep(2000);
					System.out.println("testThread1: completed");
					barrier.await();
				} catch (InterruptedException e ) {
					e.printStackTrace();
					return;
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
					return;
				}
			}
			
		};
		

		Thread thread2=new Thread("testthread2: start.."){

			@Override
			public void run() {
				try {
					System.out.println("testthread2: calculating..");
					Thread.sleep(4000);
					System.out.println("testthread2: completed");
					barrier.await();
				} catch (InterruptedException e ) {
					e.printStackTrace();
					return;
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
					return;
				}
			}
			
		};

		Thread thread3=new Thread("testthread3: start.."){

			@Override
			public void run() {
				try {
					System.out.println("testthread3: calculating..");
					Thread.sleep(6000);
					System.out.println("testthread3: completed");
					barrier.await();
				} catch (InterruptedException e ) {
					e.printStackTrace();
					return;
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
					return;
				}
			}
			
		};

		thread1.start();
		thread2.start();
		thread3.start();
	}

}
