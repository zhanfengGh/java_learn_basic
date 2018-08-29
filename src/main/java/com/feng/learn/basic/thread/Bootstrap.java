package com.feng.learn.basic.thread;

import com.feng.learn.basic.thread.test.TestObjectSize;
import lombok.extern.slf4j.Slf4j;

/**  
 * @author zhangzhanfeng 
 * @date Oct 24, 2017   
 */
@Slf4j
public class Bootstrap {
	
	public static void main(String[] args) throws InterruptedException {
		log.info("app boot start...");
		TestObjectSize.main(args);
		log.info("app boot success.");
	}

}
