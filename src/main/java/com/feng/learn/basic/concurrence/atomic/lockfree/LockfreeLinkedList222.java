package com.feng.learn.basic.concurrence.atomic.lockfree;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockfreeLinkedList222<E> {

	private final Node<E> nullNode = new Node<E>(null);
	private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(nullNode);
	private final AtomicReference<Node<E>> end = new AtomicReference<Node<E>>(nullNode);

	/**
	 * 多线程在队尾添加元素和多线程在队头取元素都不存在竞态条件 但是 多线程中既有线程添加元素又有线程取元素且队列处于只有2个元素的稳定状态时
	 * 会发生竞态条件。 暂时只能用Lock解决。
	 */
	private final Lock readWriteLock = new ReentrantLock();

	/**
	 * 队尾添加元素 算法有问题
	 * 
	 * @param item
	 */
	public void addAtEnd1(E item) {
		Node<E> newEnd = new Node<E>(item);
		Node<E> currentEnd = null;
		for (;;) {
			currentEnd = end.get();
			if (currentEnd.getNext().compareAndSet(null, newEnd)) { // 问题来了：假如执行到这一步线程崩溃，则其他线程会一致处于"等待状态"
				end.compareAndSet(currentEnd, newEnd);
				return;
			}
		}
	}

	/**
	 * 队尾添加元素 修复了addAtEnd1(E item)中出现的问题
	 * 
	 * @param item
	 */
	public void addAtEnd2(E item) {
		Node<E> newEnd = new Node<E>(item);
		Node<E> currentEnd = null;
		AtomicReference<Node<E>> nextOfCurrentEnd = null;
		for (;;) {
			currentEnd = end.get(); // 取一个瞬态
			nextOfCurrentEnd = currentEnd.getNext();
			if (nextOfCurrentEnd.get() != null) {
				end.compareAndSet(currentEnd, nextOfCurrentEnd.get());
			} else {
				if (head.get().getNext().get() == end.get()) { // 队列处于稳定状态且只有2个元素(算上哑节点）。
					readWriteLock.lock();
					try {
						if (nextOfCurrentEnd.compareAndSet(null, newEnd)) {
							end.compareAndSet(currentEnd, newEnd);
							return;
						}
					} finally {
						readWriteLock.unlock();
					}
				} else {
					if (nextOfCurrentEnd.compareAndSet(null, newEnd)) {
						end.compareAndSet(currentEnd, newEnd);
						return;
					}
				}
			}
		}
	}

	/**
	 * 队头取出元素
	 * 
	 * @return
	 */
	public E takeFromHead() {
		AtomicReference<Node<E>> nextOfNullHead = head.get().getNext();
		Node<E> realHead = null;
		AtomicReference<Node<E>> nextOfRealHead = null;
		for (;;) {
			realHead = nextOfNullHead.get(); // 取一个瞬态
			if (realHead == null) {
				return null;
			}
			nextOfRealHead = realHead.getNext();
			if (nextOfRealHead.get() != null) {
				if (nextOfNullHead.compareAndSet(realHead, nextOfRealHead.get())) {
					return realHead.getItem();
				}
			} else {
				readWriteLock.lock();
				try {
					if (end.compareAndSet(realHead, nullNode)) {
						nextOfNullHead.compareAndSet(realHead, nextOfRealHead.get());
						return realHead.getItem();
					} 
				} finally {
					readWriteLock.unlock();
				}

			}
		}
	}

	private static class Node<E> {
		private final E item;
		private final AtomicReference<Node<E>> next;

		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = new AtomicReference<Node<E>>(next);
		}

		public Node(E item) {
			this(item, null);
		}

		public AtomicReference<Node<E>> getNext() {
			return next;
		}

		public E getItem() {
			return item;
		}

	}

}
