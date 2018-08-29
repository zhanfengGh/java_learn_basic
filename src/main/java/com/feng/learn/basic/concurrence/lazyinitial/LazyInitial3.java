package com.feng.learn.basic.concurrence.lazyinitial;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

@ThreadSafe
public class LazyInitial3 {
	
	static{
		System.out.println("LazyInitial3 class initial...");
	}
	
	private static class ObjectHolder{
		private static ExpensiveObject instance;
		static{
			System.out.println("ObjectHolder class initial..");
			try {
				instance=new ExpensiveObject();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String args[]){};
    
	public static ExpensiveObject getInstance() throws InterruptedException{
		
		return ObjectHolder.instance;
	}
	
}