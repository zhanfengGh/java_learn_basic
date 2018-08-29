package com.feng.learn.basic.old2; /**
 * 
 */
/**  
 * @author zhangzhanfeng 
 * @date Aug 15, 2017   
 */
public class Test023 {
	public static void main(String[] args) {
		test();
	}
	private static int test() {
		for (int i = 0; i <  5; i++) {
			if (i == 1) {
				continue;
			} else {
				System.out.println(i);
			}
			return i;
		}
		return -1;
	}

}
