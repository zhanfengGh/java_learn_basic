package com.feng.learn.basic.thread.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**  
 * @author zhangzhanfeng 
 * @date Dec 1, 2017   
 */
@Slf4j
public class TestInterrupt {

	public static void main(String[] args) throws InterruptedException {

		final AtomicInteger COUNTER = new AtomicInteger(0);
		//test1();

		test2();
		
		//test3();

	}

	static void test3() {
		Thread main = Thread.currentThread();
		main.interrupt();
		log.info("interrupted1: {}", main.isInterrupted());
		synchronized (TestInterrupt.class) {
			log.info("interrupted2: {}", main.isInterrupted());
			try {
				TestInterrupt.class.wait(); // 立即退出，当前线程的中断状态在进入wait前已被设置为true
				log.info("interrupted3: {}", main.isInterrupted());
			} catch (InterruptedException e) {
				log.info("interrupted4: {}", main.isInterrupted());
			}
			log.info("interrupted5: {}", main.isInterrupted());
		}
		
//		new Thread(){
//
//			@Override
//			public void run() {
//				synchronized (TestInterrupt.class) {
//					TestInterrupt.class.notify();
//				}
//			}
//			
//		}.start();
		
	}

	private static volatile int sentinel = 10;
	private static Object monitor = new Object();

	
	/**
	 * 运行结果，不解，待测试。
	 * @Author zhangzhanfeng
	 * @throws InterruptedException
	 */
	static void test2() throws InterruptedException {
		Runnable task = new Runnable() {

			@Override
			public void run() {
				try {
					synchronized (this) {
						log.info("{}", this);

						log.info("before wait");
						while (TestInterrupt.sentinel > 5) {
							wait();
							log.info("after wait {}", TestInterrupt.sentinel);
						}
					}
				} catch (InterruptedException e) {
					log.info("after interrupted");
				}
			}

		};
		new Thread(task, "t1").start();
		log.info("{}", task);
		for (int i = 10; i > 0; i--) {
			TestInterrupt.sentinel--;
			synchronized (task) {
				task.notify();
			}
		}

		//Thread.sleep(10000);
	}
	
	

	static void test1() throws InterruptedException {
		Runnable task = new Runnable() {

			@Override
			public void run() {
				log.info("{} start...", Thread.currentThread().getName());
				while (!Thread.interrupted()) {
				}
				log.info("{} was interrupted. {}", Thread.currentThread().getName(), Thread.currentThread().isInterrupted());
				// 设置线程中断
				Thread.currentThread().interrupt();
				log.info("{} was interrupted. {}", Thread.currentThread().getName(), Thread.currentThread().isInterrupted());
			}

		};

		Thread t1 = new Thread(task, "Thread-1");
		t1.start();

		Thread.sleep(1000);
		t1.interrupt();
		boolean flag = t1.isInterrupted();
		log.info("t1 interrupted: {}", flag);
		Thread.sleep(1000);
		log.info("t1 interrupted: {}", t1.isInterrupted());
	}

}
