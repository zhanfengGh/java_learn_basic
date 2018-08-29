package com.feng.learn.basic.concurrence.volatiletest;

import java.util.List;


public class ServletController2 {
	
	private volatile FactorHolder holder=new FactorHolder(null, null);
	
	public void service(int number){
		String msg="";
		List<Integer> factors=holder.getFactors(number);
		if (factors==null){
			factors=FactorHolder.doFactor(number);
			holder=new FactorHolder(number, factors);
		} else{
			msg=" 命中";
		}
		msg=number+": "+factors+msg;
		System.out.println(msg);
	}

}
