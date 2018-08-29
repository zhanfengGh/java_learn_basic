package com.feng.learn.basic.thread.test;

import lombok.extern.slf4j.Slf4j;

/**  
 * @author zhangzhanfeng 
 * @date Dec 7, 2017   
 */
@Slf4j
public class TestWait {

	private static volatile String name;

	public static void main(String[] args) {
		final Object monitor = new Object();
		Runnable task = new Runnable() {

			@Override
			public void run() {
				synchronized (monitor) {
					try {
						while (true) {
							long start = System.nanoTime();
							monitor.wait(1000);
							log.info("thread awake: {}", System.nanoTime() - start);
							if (name != null) {
								break;
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};
		new Thread(task).start();
	}

}
