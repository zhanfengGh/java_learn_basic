/**
 * 
 */
package com.feng.learn.basic.old2.learn.concurrent;

import org.junit.Test;

/**  
 * @author zhangzhanfeng 
 * @date Apr 21, 2017   
 */
public class ThreadTest {

	@Test
	public void test() {
		new Thread("thread1") {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				System.currentTimeMillis();
				Thread.interrupted();
			}
			
		}.start();
	}
	
	public static void main(String[] args) {
		new Thread("thread1") {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				System.currentTimeMillis();
				System.out.println(Thread.interrupted());
			}
			
		}.start();
		
		System.out.println(System.currentTimeMillis());
		
		Runnable task = new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				System.currentTimeMillis();
				System.out.println(Thread.interrupted());
			}
			
		};
		
		Thread t = new Thread(task);
		t.setName("thread2");
		t.start();
		
		new Thread(task, "thread3").start();
		
		
	}
	
	

}
