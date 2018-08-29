/**
 * 
 */
package com.feng.learn.basic.old2.learn.concurrent;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**  
 * @author zhangzhanfeng 
 * @date Apr 22, 2017   
 */
public class BlockingQ<T> implements BlockingQueue<T> {

	private LinkedList<T> queue = new LinkedList<T>();

	private Object notEmpty = new Object();
	private Object notFull = new Object();
	
	private final int MAX_LENGTH;
	
	public BlockingQ() {
		MAX_LENGTH = Integer.MAX_VALUE;
	}
	
	public BlockingQ(int capacity) {
		MAX_LENGTH = capacity;
	}

	@Override
	public T remove() {
		synchronized (notEmpty) {
			if (size() == 0) {
				throw new NoSuchElementException();
			}
			T ret = queue.remove();
			synchronized (notFull) {
				if (size() == MAX_LENGTH) {
					notFull.notifyAll();
				}
			}
			return ret;
		}
	}

	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void put(T e) throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean offer(T e, long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T take() throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T poll(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int remainingCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int drainTo(Collection<? super T> c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super T> c, int maxElements) {
		// TODO Auto-generated method stub
		return 0;
	}

}
