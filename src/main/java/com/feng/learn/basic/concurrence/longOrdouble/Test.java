package com.feng.learn.basic.concurrence.longOrdouble;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	
	public static void main(String[] args){
		
		/**
		 * 针对64为的long或double类型的数据，java的内存模型每次只读32位的数据
		 * 
		 * 也就是说long或double类型的数据是分2次读取的
		 * 
		 * 在多线程环境中，必须用volatile 或加锁来保证数据的正确性。
		 * 
		 * 
		 */
		final Long64NotThreadSafe data=new Long64NotThreadSafe();
		
		//final Long64NotThreadSafe data=new Long64ThreadSafe();
		Runnable task1=new Runnable(){
			
			public void run() {
				while (true){
					long temp=data.getData();
					if (temp!=0L && temp!=0xffffffffffL){
						System.out.println(temp);
					}
					
					if (data.getData()==0){
						data.setData(0xffffffffffL);
					}
				}
			}
			
		};
		
		Runnable task2=new Runnable(){

			public void run() {
				while (true){
					long temp=data.getData();
					if (temp!=0L && temp!=0xffffffffffL){
						System.out.println(temp);
					}
					if (data.getData()==0xffffffffffL){
						data.setData(0L);
					}
				}
			}
			
		};
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		executor.submit(task1);
		executor.submit(task2);
		
	}
	
	

}
