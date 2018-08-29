package com.feng.learn.basic.concurrence;


import com.feng.learn.basic.thread.annotation.NotThreadSafe;

@NotThreadSafe
public class NoVisibility {
	
	private static boolean ready;
	private static int number;
	
	private static class ReadThread extends Thread{

		@Override
		public void run() {
			System.out.println("ReadThread.start()..");
			while(!ready){
				System.out.println("ReadThread.yeild()..");
				Thread.yield();
			}
			System.out.println("NoVisibility.number: "+number);
		}
		
	}
	
	public static void main(String args[]){
		new ReadThread().start();
		number=88;
		ready=true;
		
	}

}
