package com.feng.learn.basic.concurrence.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author feng_Pc
 * take()和put(E e)形成死锁
 * 
 */
public class BlockingQueue24Error<E> {
	
	private Object notEmpty=new Object();
	private Object notFull=new Object();
	private int capacity=10;
	private Queue<E> datas=new LinkedList<E>();
	
	public void put(E e) throws InterruptedException{
		synchronized (notFull){
			if (datas.size()==capacity){
				notFull.wait();
			}
			synchronized(notEmpty){
				if (datas.size()==0){
					notEmpty.notifyAll();
				}
			}
			datas.add(e);
		}
	}
	
	public E take() throws InterruptedException{
		synchronized (notEmpty){
			if (datas.size()==0){
				notEmpty.wait();
			}
			synchronized (notFull){
				if (datas.size()==capacity){
					notFull.notifyAll();
				}
			}
			return datas.poll();
		}
	}
}
