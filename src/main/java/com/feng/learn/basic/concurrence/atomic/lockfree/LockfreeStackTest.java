package com.feng.learn.basic.concurrence.atomic.lockfree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class LockfreeStackTest {
	
	public static void main(String[] args){
		
		final LockfreeStack<Integer> stack=new LockfreeStack<Integer>();
		
		final Random random=new Random();
		final CountDownLatch latch=new CountDownLatch(1);
		
		final CountDownLatch putLatch=new CountDownLatch(11);
		
		Runnable putTask=new Runnable(){

			public void run() {
				try {
					latch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stack.put(random.nextInt());
				putLatch.countDown();
			}
			
		};
		
		Callable<Integer> pullTask=new Callable<Integer>(){

			public Integer call() throws Exception {
				latch.await();
				putLatch.await();
				return stack.pull();
			}
			
		};
		ExecutorService executor=Executors.newCachedThreadPool();
		List<Future<Integer>> result=new ArrayList<Future<Integer>>();
		for (int i=0;i<10;i++){
			executor.submit(putTask);
			result.add(executor.submit(pullTask));
		}
		latch.countDown();
		
		putLatch.countDown();
		
		
		for (Future<Integer> f:result){
			try {
				System.out.println(f.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		
	}

}
