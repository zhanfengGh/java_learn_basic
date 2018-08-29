package com.feng.learn.basic.concurrence.blockingqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author feng_Pc
 * 此实现是错误的
 *
 * ?:1 是否是合格的阻塞队列？ 否
 * ?:2 take()中notEmpty.wait()时释放锁，这里的锁是哪个锁，是线程所占用的所有的锁，还是只释放notEmpty上的锁？ 只释放notEmpty锁
 * ?:3 线程触发notmpty.wait()后进入线程阻塞状态，当别的线程触发notEmpty.notifyAll()后，线程进入就绪状态，当被调度再次进入执行状态时，
 *     线程需要再次获得锁吗。。？线程的执行流程是什么样的。。？ 需要重新获取锁
 *     
 */
public class BlockingQueue23<E> {
	
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
				//datas.add(e);
			}
			datas.add(e);
		}
	}
	
	public E take() throws InterruptedException{
		synchronized (notFull){
			if (datas.size()==capacity){
				notFull.notifyAll();
			}
			synchronized (notEmpty){
				if (datas.size()==0){
					notEmpty.wait();
				}
				//return datas.poll();
			}
			return datas.poll();
		}
	}
}
