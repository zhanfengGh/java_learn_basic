package com.feng.learn.basic.concurrence.atomic.lockfree;

import java.util.concurrent.atomic.AtomicReference;

public class LockfreeLinkedList<E> {
	
	/** 队尾指针 */
	private AtomicReference<Node<E>> endPoint=new AtomicReference<Node<E>>();
	
	/** 队头指针 */
	private AtomicReference<Node<E>> headPoint=new AtomicReference<Node<E>>();
	
	/**
	 * 在链表队尾加入元素
	 * @param item 
	 */
	public void addAtEnd(E item){
		Node<E> newEndPoint=new Node<E>(item);
		Node<E> oldEndPoint=null;
		for(;;){
			oldEndPoint=endPoint.get();
			if (oldEndPoint!=null){
				AtomicReference<Node<E>> next=oldEndPoint.next;
				if (next.compareAndSet(null, newEndPoint)){   //保护endPoint
					endPoint.compareAndSet(oldEndPoint, newEndPoint); // always return true;
					return ;
				}
			} else {
				if (endPoint.compareAndSet(null, newEndPoint)){
					headPoint.compareAndSet(null, newEndPoint);
					return;
				}
			}
		}
	}
	
	/**
	 *  从队头拿出一个元素
	 * @return
	 */
	public E pullFromHead(){
		Node<E> oldHead=null;
		Node<E> newHead=null;
		for(;;){
			oldHead=headPoint.get();
			if (oldHead==null){
				// 空队列
				return null;
			} 
			newHead=oldHead.getNext();
			if (headPoint.compareAndSet(oldHead, newHead)){
				endPoint.compareAndSet(oldHead, null);
				return oldHead.getItem();
			}
		}
	}
	
	
	private static class Node<E> {

		private final E item;
		private AtomicReference<Node<E>> next=new AtomicReference<Node<E>>();

		public Node(E item) {
			this.item = item;
		}

		public Node<E> getNext() {
			return next.get();
		}

		public E getItem() {
			return item;
		}
	}

}
