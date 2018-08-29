package com.feng.learn.basic.old;

public class FinalTest3 {
	
	//宏替换
	public static final String NAME="zhang"+"feng";
	
	/**
	 * 不是宏替换 应为在编译是不能确定STR1的值
	 * static block 中的赋值实在类初始化时进行的。
	 */
	public static final String STR1;
	static {
		STR1="feng";
	}
	
	//宏替换
	public final double AGE=1000/50;
	public final String str7="178";
	
	public void test(){
		System.out.println(str7+str7=="178178");
	}
	
	/**
	 * str5,str6 不是宏替换
	 * 编译时不能确定变量的值。
	 */
	public final String str5;
	public final String str6;
	{
		str5="feng";
	}
	public FinalTest3(){
		//System.out.println(this.str6);
		str6="zhang";
	}

	
	
	public static void main(String[] args){
		FinalTest3 f=new FinalTest3();
		System.out.println(FinalTest3.NAME+NAME=="zhangfengzhangfeng"); //true
		
		f.test();
		System.out.println();
		
		//宏替换
		final String str1="178";
		String str2="178";
		String str4="178";
		System.out.println("ppp"+(f.str7==str2));
		System.out.println(f.str7+f.str7=="178178");
		System.out.println("0000"+(str2==str4)); //true
		System.out.println("111:"+(str1==str4)); //true
		System.out.println("222"+(str1==str2)); //true
		System.out.println("333"+(str1+str1=="178178")); //true
		System.out.println("444"+("178178"==str2+str2)); //false
	
		//不是宏替换
		final String str3;
		str3="hello";
		System.out.println(str3+str3=="hellohello");  //false
	}
	
}
