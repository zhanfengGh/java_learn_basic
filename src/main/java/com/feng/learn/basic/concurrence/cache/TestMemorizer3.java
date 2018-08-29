package com.feng.learn.basic.concurrence.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestMemorizer3 {
	
	public static void main(String args[]){
		Computable<String,Integer> c=new ComputableImpl();
		
		final Computable<String,Integer> cachedComputable=new Memorizer3<String, Integer>(c);
		
		final List<String> datas=new ArrayList<String>();
		
		for (int i=0;i<4;i++){
			datas.add("Hello"+i);
		}
			/**
			 * 
			 */
		for (int i=0;i<10;i++){
			final String str=datas.get(new Random().nextInt(4));
			Thread thread = new Thread(){
				
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread()+" start::::"+str);
						Integer result=cachedComputable.compute(str);
						System.out.println(Thread.currentThread()+" end::::"+str+"-"+result);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			};
			thread.start();
			
			
		}
		
	}

}
