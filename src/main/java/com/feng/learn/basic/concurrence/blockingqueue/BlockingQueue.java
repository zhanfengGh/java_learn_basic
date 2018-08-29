package com.feng.learn.basic.concurrence.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 此实现是错误的，wait()时只会释放当前锁，不会释放线程占有的其他锁
 */
public class BlockingQueue {

	private static final int MAX_CAPACITY=10;
	private Object notEmpty=new Object();
	private Object notFull=new Object();
	private Queue<Object> datas=new LinkedList<Object>();
	
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
	
	public Object take() throws InterruptedException{
		Object result=null;
		synchronized(notEmpty){
			if (datas.size()==0){
				notEmpty.wait();
			}
			synchronized(notFull){
				if (datas.size()==MAX_CAPACITY){
					notFull.notifyAll();
				}
				result=datas.poll();
			}
		}
		return result;
	}
}
