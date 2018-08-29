package com.feng.learn.basic.old2.learn;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhangzhanfeng 
 * @date May 31, 2017   
 */
public class Main {
	public static void main(String[] args) {
		final int timeout = Integer.valueOf(args[0]);
		final Object lock = new Object();
		final AtomicBoolean flag = new AtomicBoolean(false);
		new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock) {
					flag.compareAndSet(false, true); 
					lock.notifyAll();
				}
			}

		}.start();
		synchronized (lock) {
			try {
				while (!flag.get()) {
					lock.wait(2000);
					System.out.println("lock back");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
