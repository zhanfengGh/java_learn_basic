package com.feng.learn.basic.concurrence.lazyinitial;

import com.feng.learn.basic.concurrence.TaskUtils;

public class Test {
	
	public static void main(String[] args){
		
		Runnable task=new Runnable(){

			public void run() {
				ExpensiveObject instance=null;
				try {
					instance = LazyInitial3.getInstance();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(instance);
			}
			
		};
		
		for (int i=0;i<10;i++){
			TaskUtils.submit2FixedPool(task);
		}
		
		
	}
	
	

}
