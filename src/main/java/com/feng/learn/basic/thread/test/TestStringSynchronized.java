package com.feng.learn.basic.thread.test;

import lombok.extern.slf4j.Slf4j;

/**  
 * @author zhangzhanfeng 
 * @date Dec 1, 2017   
 */
@Slf4j
public class TestStringSynchronized {
	private static final String str1 = "abc";
	private static final String str2 = "abc";
	private static final String str3 = new String("abc");
	
	
	public static void main(String[] args) {
		log.info("str1 == str2: {}", str1 == str2);
		log.info("str1 == str3: {}", str1 == str3);
		new Thread(new Runnable(){

			@Override
			public void run() {
				aa("aa");
			}
			
		}).start();
		new Thread(new Runnable(){

			@Override
			public void run() {
				aa("aa");
			}
			
		}).start();
	}
	
	
	public static void aa(String str) {
		log.info("{}");
		synchronized (str) {
			log.info("{}", str);
		}
	}

}
