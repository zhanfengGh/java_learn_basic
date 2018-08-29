package com.feng.learn.basic.concurrence.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class Test {
	
	private static final int MAX_BALANCE=100;
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		final AccountServiceI service=new AccountService4();
		final Account[] accounts=Account.getAccounts(3,MAX_BALANCE);
		final Random random=new Random();
		final CountDownLatch startGate=new CountDownLatch(1001);
		
		ExecutorService executor=Executors.newCachedThreadPool();
		
		Callable<Boolean> task=new Callable<Boolean>(){

			public Boolean call() throws Exception {
				startGate.await();
				Account from=accounts[random.nextInt(accounts.length)];
				Account to=accounts[random.nextInt(accounts.length)];
				if (from==to){
					return false;
				}
				return service.transferMoney(from, to, random.nextInt(MAX_BALANCE));
			}
			
		};
		int transferCount=0;
		int transferSuccess=0;
		int transferFail=0;
		List<Future<Boolean>> results=new ArrayList<Future<Boolean>>();
		//模拟1000个ATM转账请求
		for (int i=0;i<1000;i++) {
			Future<Boolean> result=executor.submit(task);
			results.add(result);
			startGate.countDown();
		}
		//一起启动所有线程
		startGate.countDown();
		for (int i=0;i<1000;i++){
			Future<Boolean> result=results.get(i);
			if (result.get()){
				transferSuccess++;
			} else {
				transferFail++;
			}
			transferCount++;
		}
		System.out.println("transferSuccess: "+transferSuccess+"\n"
				+ "transferFail: "+transferFail+"\n"
						+ "transferCount: "+transferCount);
		
	}
	
	
}
