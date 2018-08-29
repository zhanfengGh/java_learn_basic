package com.feng.learn.basic.concurrence.blockingqueue;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author feng_Pc
 * 
 * 空即等待阻塞队列	
 * 
 */
@ThreadSafe
public class BlockingQueue21<E> {
	
	private Object notEmpty=new Object();
	
	private Queue<E> datas=new LinkedList<E>();
	
	public E take() throws InterruptedException{
		synchronized(notEmpty){
			if (datas.size()==0)
				notEmpty.wait();
			return datas.poll();
		}
	}
	
	public void put(E e){
		synchronized(notEmpty){
			if (datas.size()==0)
				notEmpty.notifyAll();
			datas.add(e);
		}
	}

}
