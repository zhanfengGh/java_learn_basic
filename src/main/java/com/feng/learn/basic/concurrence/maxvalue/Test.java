package com.feng.learn.basic.concurrence.maxvalue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	
	public static void main(String[] args){
		
		//闭锁保证所有进程一起启动
		final CountDownLatch startGate=new CountDownLatch(6);
		final CountDownLatch endGate=new CountDownLatch(5);
		
		final MaxValue maxValue=new MaxValue();
		Random random=new Random();
		//模拟数据
		final List<Integer> datas=new ArrayList<Integer>();
		for (int i=0;i<10000;i++){
			datas.add(random.nextInt(100));
		}
		System.out.println(datas);
		
		Runnable task=new Runnable(){
			public void run() {
				try {
					startGate.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("线程: "+" 启动.");
				for (int i=0;i<datas.size();i++){
					maxValue.setMaxValue(datas.get(i));
				}
				endGate.countDown();
			}
		};
		ExecutorService executor=Executors.newCachedThreadPool(); 
		//启动5个线程测试
		for (int i=0;i<5;i++){
			executor.submit(task);
			startGate.countDown();
			System.out.println("......");
		}
		
		startGate.countDown();
		System.out.println("线程已全部一起启动");
		try {
			endGate.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(maxValue.getMaxValue());
	}

}
