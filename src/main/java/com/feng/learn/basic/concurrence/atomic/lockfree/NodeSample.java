package com.feng.learn.basic.concurrence.atomic.lockfree;

public class NodeSample<E> {

	private final E item;
	private NodeSample<E> next;

	public NodeSample(E item) {
		this.item = item;
	}

	public NodeSample<E> getNext() {
		return next;
	}

	public void setNext(NodeSample<E> next) {
		this.next = next;
	}

	public E getItem() {
		return item;
	}
}
