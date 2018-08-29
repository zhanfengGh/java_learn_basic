package com.feng.learn.basic.concurrence.volatiletest;


import com.feng.learn.basic.thread.annotation.Immutable;

import java.util.ArrayList;
import java.util.List;

@Immutable
public class FactorHolder {
	
	/** Integer 本身是不可变类  */
	private final Integer number;
	private final List<Integer> factors;
	
	public FactorHolder(Integer number, List<Integer> factors){
		this.number=number;
		this.factors=factors;
	}
	
	/** 这里可以把Integer 的引用发布出去（Integer是不可变类） */
	public int getNubmer(){
		return number;
	}

	/** 这里不能把List<Integer>的引用发布出去，因为它是一个个可变类 */
	public List<Integer> getFactors() {
		return new ArrayList<Integer>(factors);
	}
	
	public List<Integer> getFactors(int number){
		if (factors==null || this.number!=number){
			return null;
		} else{
			return new ArrayList<Integer>(factors);
		}
	}
	
	public static List<Integer> doFactor(int number){
		List<Integer> factors=new ArrayList<Integer>();
		
		for (int i=2;i<=number;i++){
			while (i!=number){
				if (number%i!=0){
					break;
				} else{
					factors.add(i);
					number=number/i;
				}
			}
		}
		factors.add(number);
		
		return factors;
	}
	
	public static void main(String[] args){
		int number=68880;
		System.out.println(doFactor(number));
	}
	
}
