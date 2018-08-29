package com.feng.learn.basic.old;

public class Immutable {
	
	private final Integer number;
	
	public Immutable(Integer number){
		this.number=number;
	}
	
	public static void main(String[] args){
		Immutable test=new Immutable(555);
		
		System.out.println(test.number);
		
		Integer i2=test.number;
		i2=20;
		System.out.println(test.number);
		System.out.println(i2);
				
	}

}
