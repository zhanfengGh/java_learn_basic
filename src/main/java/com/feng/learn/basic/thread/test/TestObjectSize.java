package com.feng.learn.basic.thread.test;

import java.util.ArrayList;
import java.util.List;

/**  
 * @author zhangzhanfeng 
 * @date Nov 30, 2017   
 */
public class TestObjectSize {

	/**
	 * 实验结果：
	 * 一个Object 对象在heap中占用16个byte
	 * @Author zhangzhanfeng
	 * @param args
	 * @throws InterruptedException
	 */
	
	public static void main(String[] args) throws InterruptedException {
		int count = Integer.valueOf(args[0]);

		List<Object> list = new ArrayList<>((int) count);
		for (int i = 0; i < count; i++) {
			list.add(new Object());
		}
		synchronized (list) {
			list.wait();
		}
	}
}
