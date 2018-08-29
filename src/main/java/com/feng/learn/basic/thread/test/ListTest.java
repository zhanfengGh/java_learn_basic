package com.feng.learn.basic.thread.test;

import java.util.ArrayList;
import java.util.List;

/**  
 * @author zhangzhanfeng 
 * @date Dec 5, 2017   
 */
public class ListTest {

	public static void main(String[] args) {
		int size = 1024 * 1024;
		List<Long> list = new ArrayList<>(1024 * 1024 + 10);
		for (int i = 0; i < size; i++) {
			list.add((long) i);
		}

		long time = 0;
		for (long i = 0; i < size; i++) {
			long start = System.nanoTime();
			list.contains(i);
			time += (System.nanoTime() - start);
		}
		System.out.println(time / size);
	}
}
