package com.feng.learn.basic.thread.test;

import lombok.extern.slf4j.Slf4j;

/**  
 * @author zhangzhanfeng 
 * @date Dec 1, 2017   
 */
@Slf4j
public class TestVolatile {
	
	private Person p = new Person();
	
	public static void main(String[] args) {
		final TestVolatile tv = new TestVolatile();
		new Thread() {

			@Override
			public void run() {
				log.info("before change: {}", tv.p);
				tv.p = new Person("feng");
				log.info("after change: {}", tv.p);
				System.out.println("");
			}
			
		}.start();
		
		new Thread() {

			@Override
			public void run() {
				log.info("get thread start");
				log.info("{}", tv.p);
				log.info("get thread end");
			}
			
		}.start();
	}
	
	static class Person {
		String name = "zhang";
		int age;
		
		public Person() {}
		public Person(String name) {
			this.name = name;
		}
	}

}
