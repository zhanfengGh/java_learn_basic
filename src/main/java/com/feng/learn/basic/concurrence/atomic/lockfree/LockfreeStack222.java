package com.feng.learn.basic.concurrence.atomic.lockfree;

import java.util.concurrent.atomic.AtomicReference;

public class LockfreeStack222<E> {

    private final AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    /**
     * 入栈
     *
     * @param item
     */
    public void push(E item) {
        Node<E> newTop = new Node<E>(item);
        Node<E> currentTop = null;
        for (; ; ) {
            currentTop = top.get();
            newTop.setNext(currentTop);
            if (top.compareAndSet(currentTop, newTop)) {
                return;
            }
        }
    }

    /**
     * 出栈
     *
     * @return
     */
    public E pop() {
        Node<E> currentTop = null;
        Node<E> newTop = null;
        for (; ; ) {
            currentTop = top.get();
            if (currentTop == null) {
                return null;
            }
            newTop = currentTop.getNext();
            if (top.compareAndSet(currentTop, newTop)) {
                return currentTop.getItem();
            }
        }

    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public Node(E item) {
            this(item, null);
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public E getItem() {
            return item;
        }


    }

}
