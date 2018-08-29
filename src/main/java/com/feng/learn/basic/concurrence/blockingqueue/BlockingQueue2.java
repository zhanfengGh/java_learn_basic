package com.feng.learn.basic.concurrence.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue2 {

	private static final int MAX_CAPACITY=10;
	private Object notEmpty=new Object();
	private Object notFull=new Object();
	private Queue<Object> datas=new LinkedList<Object>();
	
	/** 会形成死锁  */
	public void put(Object object) throws InterruptedException{
		synchronized(notEmpty){
			if (datas.size()==0){
				notEmpty.notifyAll();
			}
			synchronized(notFull){
				if (datas.size()==MAX_CAPACITY){
					notFull.wait();
				}
				datas.add(object);
			}
		}
	}
	
	/** 会形成死锁  */
	public Object take() throws InterruptedException{
		Object result=null;
		synchronized(notFull){
			if (datas.size()==MAX_CAPACITY){
				notFull.notifyAll();
			}
			synchronized(notEmpty){
				if (datas.size()==0){
					notEmpty.wait();
				}
				result=datas.poll();
			}
		}
		return result;
	}
}
