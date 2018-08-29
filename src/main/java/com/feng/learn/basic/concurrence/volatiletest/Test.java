package com.feng.learn.basic.concurrence.volatiletest;

import com.feng.learn.basic.concurrence.TaskUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
	
	public static void main(String[] args){
		
		final ServletController3 handler=new ServletController3();
		
		Random random=new Random();
		final List<Integer> datas=new ArrayList<Integer>();
		for (int i=0;i<20;i++){
			int temp=random.nextInt(25);
			datas.add(temp);
		}
		System.out.println("测试数据："+datas);
		
		// 模拟Servlet多线程处理
		for (int i=0;i<datas.size();i++){
			final int temp=datas.get(i);
			TaskUtils.submit2FixedPool(new Runnable(){

				public void run() {
					handler.service(temp);
				}
				
			});
		}
	}

}
