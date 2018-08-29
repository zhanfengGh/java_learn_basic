package com.feng.learn.basic.thread;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 这个实现是错误的！！！！！
 *
 * @author zhangzhanfeng 
 * @date Dec 8, 2017   
 */
public class ArrayBlockingQueue2<E> implements BlockingQueue<E> {

	private final E[] data; //存储数据
	private int head; //队头指针
	private int tail; //队末指针

	private Object emptyLock = new Object();
	private Object fullLock = new Object();

	private void doAddToTail(E e) {
		data[tail++ % data.length] = e;
		emptyLock.notifyAll();
	}

	private E doRemoveFromHead() {
		int index = head++ % data.length;
		E ret = data[index];
		data[index] = null;
		fullLock.notifyAll();
		return ret;
	}

	@SuppressWarnings("unchecked")
	public ArrayBlockingQueue2(int capacity) {
		data = (E[]) new Object[capacity];
	}

	@Override
	public synchronized E remove() {
		if (size() > 0) {
			return doRemoveFromHead();
		}
		throw new NoSuchElementException();
	}

	@Override
	public synchronized E poll() {
		if (size() > 0) {
			return doRemoveFromHead();
		}
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized int size() {
		return tail - head;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
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
	public boolean addAll(Collection<? extends E> c) {
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
	public boolean add(E e) {
		checkNull(e);
		synchronized (emptyLock) {
			synchronized (fullLock) {
				if (size() == data.length) {
					throw new IllegalStateException("queue is full");
				}
				doAddToTail(e);
				return true;
			}
		}
	}

	private void checkNull(Object o) {
		if (o == null) {
			throw new NullPointerException("object is null");
		}
	}

	@Override
	public boolean offer(E e) {
		checkNull(e);
		synchronized (emptyLock) {
			synchronized (fullLock) {
				if (size() == data.length) {
					return false;
				}
				doAddToTail(e);
				return true;
			}
		}
	}

	@Override
	public void put(E e) throws InterruptedException {
		checkNull(e);
		synchronized (emptyLock) {
			synchronized (fullLock) {
				while (size() == data.length) {
					fullLock.wait();
				}
				doAddToTail(e);
			}
		}
		
	}

	@Override
	public synchronized boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		checkNull(e);
		for (;;) {
			if (size() < data.length) {
				doAddToTail(e);
				return true;
			}
			if (timeout <= 0) {
				return false;
			}
			long start = System.currentTimeMillis();
			wait(unit.toMillis(timeout));
			timeout = timeout - (System.currentTimeMillis() - start);
		}
	}

	@Override
	public synchronized E take() throws InterruptedException {
		while (size() == 0) {
			wait();
		}
		return doRemoveFromHead();
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		for (;;) {
			if (size() > 0) {
				return doRemoveFromHead();
			}
			if (timeout <= 0) {
				return null;
			}
			long start = System.currentTimeMillis();
			wait(unit.toMillis(timeout));
			timeout = timeout - (System.currentTimeMillis() - start);
		}
	}

	@Override
	public synchronized int remainingCapacity() {
		return data.length - size();
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
	public int drainTo(Collection<? super E> c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		// TODO Auto-generated method stub
		return 0;
	}

}
