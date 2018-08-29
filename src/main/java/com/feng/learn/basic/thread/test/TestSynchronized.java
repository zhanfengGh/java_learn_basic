package com.feng.learn.basic.thread.test;

import lombok.extern.slf4j.Slf4j;

/**  
 * @author zhangzhanfeng 
 * @date Nov 30, 2017   
 */
@Slf4j
public class TestSynchronized {

	private static final TestSynchronized instance = new TestSynchronized();

	static class Task1 implements Runnable {

		@Override
		public void run() {
			System.out.println("");
			synchronized (TestSynchronized.class) {
				String name = Thread.currentThread().getName();
				log.info("{}: obtain monitor(TestSynchronized.class).", name);
				// sleep 不会释放锁
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("{}: release monitor(TestSynchronized.class).", name);
			}
		}

	}

	static class Task2 implements Runnable {

		@Override
		public void run() {
			synchronized (instance) {
				String name = Thread.currentThread().getName();
				log.info("{}: obtain monitor(a instance of TestSynchronized).", name);
				// sleep 不会释放锁
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("{}: release monitor(a instance of TestSynchronized).", name);
			}
		}

	}

	/** 
	 * 实验结果：
	 * 静态方法 
	 */
	public static void main(String[] args) {
		log.info("{}", instance.getClass() == TestSynchronized.class);
		Task1 t1 = new Task1();
		Task2 t2 = new Task2();
		new Thread(t1, "thread-1").start();
		new Thread(t1, "thread-2").start();
		new Thread(t2, "thread-3").start();
		new Thread(t2, "thread-4").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				TestSynchronized.aaa();
			}
			
		}, "t1").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				instance.bb();
			}
			
		}, "t2").start();

	}

	public synchronized static void aaa() {
		log.info("aaa");
	}
	
	public synchronized void bb() {
		log.info("bb");
	}

}
