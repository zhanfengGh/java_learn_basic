package com.feng.learn.basic.thread.test;

/**  
 * @author zhangzhanfeng 
 * @date Nov 30, 2017   
 */
public class TestJoin {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new MyThread("thread-1");
		Thread t2 = new MyThread("thread-2");
		t1.start();
		t2.start();
		printf();
		t1.join();
		t2.join();
		System.out.println("sdfasd");
		
	}
	
	private static void printf() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + ": " + i);
		}
	}

	static class MyThread extends Thread {

		public MyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			printf();
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
