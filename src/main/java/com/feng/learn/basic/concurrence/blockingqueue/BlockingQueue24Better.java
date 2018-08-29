package com.feng.learn.basic.concurrence.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue24Better {
	
	private static final int MAX_CAPACITY=10;
	
	private Lock lock=new ReentrantLock();
	private Condition notEmpty=lock.newCondition();
	private Condition notFull=lock.newCondition();
	private Queue<Object> datas=new LinkedList<Object>();
	
	public void put(Object object) throws InterruptedException{
		lock.lock();
		try{
			if (datas.size()==0){
				notEmpty.signalAll();
			}
			if (datas.size()==MAX_CAPACITY){
				notFull.await();
			}
			datas.add(object);
		}finally{
			lock.unlock();
		}
	}
	
	public Object take() throws InterruptedException{
		lock.lock();
		try{
			if (datas.size()==0){
				notEmpty.await();
			}
			if (datas.size()==MAX_CAPACITY){
				notFull.signalAll();
			}
			return datas.poll();
		}finally{
			lock.unlock();
		}
	}

}
