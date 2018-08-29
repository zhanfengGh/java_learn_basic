package com.feng.learn.basic.old;

public class FinalTest4 {
	
	String str1;
	final String str2;
	
	{
		//System.out.println(this.str2);
		this.str2="feng";
	}
	
	public FinalTest4(){
		System.out.println(str1);
		System.out.println(str2);
		
	}
	public static void main(String[] args){
		new FinalTest4();
	}

}
