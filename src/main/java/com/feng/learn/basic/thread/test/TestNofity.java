package com.feng.learn.basic.thread.test;

import lombok.extern.slf4j.Slf4j;

/**  
 * @author zhangzhanfeng 
 * @date Nov 30, 2017   
 */
@Slf4j
public class TestNofity {

	private static final Object monitor = new Object();

	public static void main(String[] args) throws InterruptedException {

		Task t = new Task();
		for (int i = 1; i < 10; i++) {
			new Thread(t, "thread-" + i).start();
		}
		String name = Thread.currentThread().getName();
		Thread.sleep(3000);
		synchronized (monitor) {
			log.info("{}: obtain monitor", name);
			log.info("{}: before notify", name);
			monitor.notifyAll();
			log.info("{}: after notify", name);
			for (int i = 10; i < 100; i++) {
				new Thread(t, "thread-" + i).start();
			}
			log.info("{}: exit monitor", name);
		}
		
	}

	static class Task implements Runnable {

		@Override
		public void run() {
			synchronized (monitor) {
				String name = Thread.currentThread().getName();
				log.info("{}: obtain monitor", name);
				try {
					log.info("{}: before wait", name);
					monitor.wait(5000);
					log.info("{}: after wait", name);					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("{}: exit monitor", name);
			}
		}
	}

}
