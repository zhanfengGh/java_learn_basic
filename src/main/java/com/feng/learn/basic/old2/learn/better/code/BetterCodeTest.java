/**
 * 
 */
package com.feng.learn.basic.old2.learn.better.code;


/**
 * @author feng
 *
 */

/**
 * 方法重写的要求
 * 0 》 相同的方法签名
 * 1 》 相同或更大的访问权限
 * 2 》 相同或更小的返回值类型
 * 3 》 相同或更小的抛出异常
 *
 */

public class BetterCodeTest {

	/**
	 * 用大写的L标记long类型
	 * 
	 */
	public void ruleOne() {
		long i = 1l;
		long l = 1L; //大写的L用于标记long类型
		//float f = 5.004; //编译报错
		float f2 = 5.004F;
		System.out.println(i + l);
	}

	/**
	 * 小心3元操作符的转型
	 * 
	 */
	//@Test
	public void ruleTwo() {
		int i = 80;
		String s1 = String.valueOf(i < 100 ? 80 : 100); //s1 = "80";
		String s2 = String.valueOf(i < 100 ? 80 : 100.0); //s2 = "80.0"
		System.out.println(s1.equals(s2)); //false
	}

	/**
	 * 小心带有变长参数的方法重载
	 * 
	 * 
	 */
	public void ruleThree() {
		Vargs.methodA("", 1);
		Vargs.methodA("", "");
		//Vargs.methodA(""); //无法确定调用哪个方法
		//Vargs.methodA("", null); //无法确定调用哪个方法
	}

	static class Vargs {
		public static void methodA(String str, Integer... integers) {
			System.out.println("methodA(String,Integer...):void");
		}

		public static void methodA(String str, String... strings) {
			System.out.println("methodA(String,String...):void");
		}
	}


}
