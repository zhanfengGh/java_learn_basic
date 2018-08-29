package com.feng.learn.basic.old.test;

class Price{
	static Price INSTANCE;
	/**
	 * 宏替换4个条件：
	 * 1 static 类成员
	 * 2 final 类型的类成员
	 * 3   声明时赋值
	 * 4   赋值表达式在编译时能确定
	 */
    private static final int totalPrice=20;
	static{
		INSTANCE=new Price(2);
		//totalPrice=20;
	}
	private int currentPrice;
	private final int test=5;
	public Price(int count){
		currentPrice=totalPrice-count;
	}
	public int getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}
	
}

public class PriceTest {
	public static void main(String[] args){
		System.out.println(Price.INSTANCE.getCurrentPrice());
	}
}
