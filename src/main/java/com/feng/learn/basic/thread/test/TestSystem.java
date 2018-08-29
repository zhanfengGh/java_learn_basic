package com.feng.learn.basic.thread.test;
/**  
 * @author zhangzhanfeng 
 * @date Dec 1, 2017   
 */
public class TestSystem {
	public static void main(String[] args) {
		for (int i = 0; i < Runtime.getRuntime().availableProcessors() * 2; i++) {
			new Thread() {

				@Override
				public void run() {
					for (;;) {
						//System.currentTimeMillis();
					}
				}
				
			}.start();
		}
	}
}
