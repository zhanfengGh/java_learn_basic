package com.feng.learn.basic.concurrence.blockingqueue;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author feng_Pc
 *
 * 满即等待
 */
@ThreadSafe
public class BlockingQueue22<E> {

	private Object notFull=new Object();
	private int capacity=10;
	private Queue<E> datas=new LinkedList<E>();
	
	public E take(){
		synchronized(notFull){
			if (datas.size()==capacity)
				notFull.notifyAll();
			return datas.poll();
		}
	}
	
	public void put(E e) throws InterruptedException{
		synchronized(notFull){
			if (datas.size()==capacity)
				notFull.wait();
			datas.add(e);
		}
	}

}
