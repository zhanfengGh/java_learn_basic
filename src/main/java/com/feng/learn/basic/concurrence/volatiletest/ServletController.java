package com.feng.learn.basic.concurrence.volatiletest;

import java.util.List;


public class ServletController {
	
	private volatile FactorHolder holder;
	
	public void service(int number){
		String msg="";
		FactorHolder lastHolder=holder;
		List<Integer> factors=null;
		if (lastHolder==null || lastHolder.getNubmer()!=number){
			factors=FactorHolder.doFactor(number);
			holder=new FactorHolder(number, factors);
		} else{
			factors=lastHolder.getFactors();
			msg=" 命中";
		}
		msg=number+": "+factors+msg;
		System.out.println(msg);
	}

}
