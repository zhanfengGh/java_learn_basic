package com.feng.learn.basic.concurrence;

import java.math.BigInteger;
import java.util.Arrays;

public class OneValueCache {

	private final BigInteger lastNumber;
	private final BigInteger[] factors;
	
	public OneValueCache(BigInteger lastNumber,BigInteger[] factors){
		this.lastNumber=lastNumber;
		this.factors=Arrays.copyOf(factors, factors.length);
	}
	
	public BigInteger[] getFactors(BigInteger number){
		if (number.equals(lastNumber)){
			return Arrays.copyOf(factors, factors.length);
		}
		return null;
	}
}
