package com.feng.learn.basic.old;

public class FinalTest5 {
	
	/**
	 *  宏替换 
	 *  1 static 
	 *  2 final 
	 *  3   定义时赋值
	 *  4   定义时的赋值表达式在编译时可以确定 
	 * 
	 */
	static final String name="zhangfeng";  //宏替换
	static final String myName="I'm"+name; //宏替换
	
	static final String yourName;  //不是宏替换
	
	static{
		System.out.println(FinalTest5.yourName);
		// System.out.println(yourName); 区别。。。。？
		
		yourName="youName";
	}
	
	static final int length="I'm zhangfeng".length(); //不是宏替换
	static String hisName="hisName";
	
	/**
	 * 上面的代码实际上是如下形式：
	 * 编译器会把非宏替换的类变量的赋值都放到static{}块中进行赋初值。
	 * 注意 static {} 块中的执行顺序和定义类变量的顺序一致
	 * 
	 * 
	 * 	static final String name="zhangfeng";  //宏替换 
		static final String myName="I'm"+name; //宏替换
	
		static final String yourName;  //不是宏替换
		static final int length; //不是宏替换
		static String hisName;
	
		static{
			yourName="youName";
			length="I'm zhangfeng".length()
			hisName="hisName"
		}
	 * 
	 */
	
	
	// ************************分割线************************
	final int number=5; //编译时number的值可以确定下来， 变量number会被编译器去掉
	
	final int number2; 
	
	int a=5;
	int b;
	
	public FinalTest5(){
		System.out.println("Hello,World");
		System.out.println(number);
		System.out.println(number2);
	}
	
	{
		System.out.println(b);
		//System.out.println(this.number2);
		//System.out.println(number2); 区别在哪里。。。。。。？
		number2=100;
		b=10;
	}
	
	final int strLength="I'm feng".length(); // 编译时strLength的值不能确定下来
	
	/**
	 * 以上代码相当于
	 * 编译器会把所有需要初始化的变量 （定义时直接赋值的和在{}非静态块中的初始化代码都放到构造器中。
	 * n编译器会把number变量去掉（final类型，定义时赋值，且编译时可以确定下来）
	 * 
	 	final int number=5; //编译时number的值可以确定下来， 变量number会被编译器去掉
		final int number2;
		int a;
		int b;
		final int strLength;
		
		public FinalTest5(){
			a=5;
			number2=100;
			b=10;
			strLength="I'm feng".length();
			System.out.println("Hello,World");
			System.out.println(5);
			System.out.println(number2);
		}
	 * 
	 */
	
	public static void main(String[] args){
		new FinalTest5();
	}

}
