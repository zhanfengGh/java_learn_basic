/**
 * 
 */
package com.feng.learn.basic.old2.learn.thread;

/**  
 * @author zhangzhanfeng 
 * @date Jun 5, 2017   
 */
public class Main {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread("testThread") {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
						System.out.println("dfsdf");
						System.out.println("dfsdf");
					}
				}

			};
			t.setDaemon(true);
			t.start();
		}
	}

}
