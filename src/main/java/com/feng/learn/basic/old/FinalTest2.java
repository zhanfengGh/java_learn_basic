package com.feng.learn.basic.old;


class Price{
	
	static final Price INSTANCE=new Price(2.9);
	static final double initPrice=20;
	
	static{
		//initPrice=20;
	}
	
	double currentPrice;
	
	public Price(double cutPrice){
		this.currentPrice=initPrice-cutPrice;
	}
}

public class FinalTest2 {
	
	public static void main(String[] args){
		System.out.println(Price.INSTANCE.currentPrice);
	}

}
