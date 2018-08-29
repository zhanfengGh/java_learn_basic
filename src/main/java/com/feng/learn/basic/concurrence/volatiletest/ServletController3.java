package com.feng.learn.basic.concurrence.volatiletest;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 
 * @author feng_Pc
 * 
 * 相比ServletController2 原子类提供更好的可见性。
 */
public class ServletController3 {
	
	private AtomicReference<FactorHolder> holder=new AtomicReference<FactorHolder>(new FactorHolder(null, null));
	
	//private volatile FactorHolder holder=new FactorHolder(null, null);
	
	public void service(int number){
		String msg="";
		List<Integer> factors=holder.get().getFactors(number);
		if (factors==null){
			factors=FactorHolder.doFactor(number);
			holder.set(new FactorHolder(number, factors));
		} else{
			msg=" 命中";
		}
		msg=number+": "+factors+msg;
		System.out.println(msg);
	}

}
