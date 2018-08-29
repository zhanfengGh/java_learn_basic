package com.feng.learn.basic.concurrence.atomic.lockfree;

import java.util.concurrent.atomic.AtomicReference;

public class LockfreeLinkedListBetter<E> {
	
	private Node<E> dummy=new Node<E>(null, null);

	private AtomicReference<Node<E>> headPoint=new AtomicReference<Node<E>>(dummy);
	private AtomicReference<Node<E>> endPoint=new AtomicReference<Node<E>>(dummy);
	
	/**
	 * 在队尾添加元素
	 * 
	 * 算法精髓：
	 * 只要保证新加的节点放到当前队尾的next变量中，即可成功返回。
	 * endPoint 的推动可以由本线程做，也可以由别的线程来完成。
	 * @param e
	 */
	public void addAtEnd(E e){
		Node<E> nodeToAdd=new Node<E>(e,null);
		Node<E> currentEndNode;
		Node<E> realEndNode;
		for (;;){
			currentEndNode=endPoint.get();
			realEndNode=currentEndNode.getNext().get();
			if (realEndNode!=null){ //A 判断当前队尾的next是否已被向后推动（指向新的Node）；如果已经指向新的节点，把endPoint向后推动的队尾。
				endPoint.compareAndSet(currentEndNode, realEndNode); //B 推动endPoing指针到新的队尾。
			} else {
				if (currentEndNode.getNext().compareAndSet(null, nodeToAdd)){ //C 尝试推动当前队尾的next指针指向新的队尾节点，成功的话尝试D
					endPoint.compareAndSet(currentEndNode, nodeToAdd); //D 尝试推动队尾指针endPoint指向新的队尾，无论成功，都会返回。不成功说明别的线程已经帮我做过队尾指针的推动了。
					return;
				}
			}
		}
	}
	
	/**
	 * 从队头取出一个元素
	 * 比较简单，只需要维护一个指针就可以了
	 * @return
	 */
	public E pullFromHead(){
		AtomicReference<Node<E>> nextOfHeadPoint;
		Node<E> realHeadNode;
		for(;;){
			nextOfHeadPoint=headPoint.get().getNext();
			realHeadNode=nextOfHeadPoint.get();
			if (nextOfHeadPoint.compareAndSet(realHeadNode, realHeadNode.getNext().get())){
				return realHeadNode.getItem();
			}
		}
	}
	
	
	/**
	 * 
	 * @author feng_Pc
	 * 节点模型
	 * 注意next的类型为AtomicReference<Node<E>>
	 *
	 * @param <E>
	 */
	private static class Node<E>{
		private final E item;
		private AtomicReference<Node<E>> next;
		
		public Node(E item, Node<E> nextNode){
			this.item=item;
			this.next=new AtomicReference<Node<E>>(nextNode);
		}

		public AtomicReference<Node<E>> getNext() {
			return next;
		}

		public E getItem() {
			return item;
		}
		
	}
	
	
	
}
