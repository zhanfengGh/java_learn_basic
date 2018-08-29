package com.feng.learn.basic.old2; /**
 * 
 */
/**  
 * @author zhangzhanfeng 
 * @date Jun 17, 2017   
 */
public final class Test01 {
	private int i;
	private final String name; 
	
	public Test01() {
		this.i =0;
		this.name = null;
	}
	
	public Test01(int i, String name) {
		this.i = i;
		this.name = name; 
	}

	public static void main(String[] args) {
		Test01 test01 = new Test01(5, "hello world");
		test01.i = 5;
	}
	
}

class Test02 {
	public static void main(String args[]) {
		System.out.println("test02");
	    String str = new String("feng");
	}
}

interface A {
	int i = 5;
	void method();
}

