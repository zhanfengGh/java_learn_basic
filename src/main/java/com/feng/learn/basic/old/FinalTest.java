package com.feng.learn.basic.old;

public class FinalTest {
	
	public static final String className;
	public final static int count;
	
	static{
		//???????????????????????
		System.out.println(FinalTest.className+"\t"+FinalTest.count);
		className="FinalTest";
		count=0;
		System.out.println(className+"\t"+count);
	}
	
	private final int age;
	private final String name;
	
	//？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
	public FinalTest(){
		//System.out.println(this.name+"\t"+this.age);
		this.age=18;
		this.name="feng";
		System.out.println(name+"\t"+age);
	}
	
	public FinalTest(String name,int age){
		this.name=name;
		this.age=age;
	}
	{
		
	}
	
	public static void main(String[] args){
		System.out.println("main()..");
		new FinalTest();
	}

}
