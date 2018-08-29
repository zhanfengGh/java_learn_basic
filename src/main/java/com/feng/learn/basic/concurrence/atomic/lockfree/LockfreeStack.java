package com.feng.learn.basic.concurrence.atomic.lockfree;


import com.feng.learn.basic.thread.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;


/**
 * @param <E>
 * @author feng_Pc
 * <p>
 * 非阻塞算法实现多线程堆栈。
 */

@ThreadSafe
public class LockfreeStack<E> {

    /**
     * 定义一个原子变量来保存栈顶引用
     */
    private AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    /**
     * 元素入栈
     *
     * @param e 需要入栈的元素
     */
    public void put(E e) {
        Node<E> newTopNode = new Node<E>(e);
        Node<E> oldTopNode = null;
        for (; ; ) {
            oldTopNode = top.get();
            newTopNode.setNext(oldTopNode);
            if (top.compareAndSet(oldTopNode, newTopNode))
                return;
        }
    }

    /**
     * 从栈中取出一个元素
     *
     * @return E 栈顶元素  or null
     */
    public E pull() {
        Node<E> oldTopNode = null;
        Node<E> newTopNode = null;
        for (; ; ) {
            oldTopNode = top.get();
            if (null == oldTopNode)
                return null;
            newTopNode = oldTopNode.getNext();
            if (top.compareAndSet(oldTopNode, newTopNode)) {
                return oldTopNode.getItem();
            }
        }
    }

    private static class Node<E> {
        private final E item;
        private Node<E> next;

        public Node(E item) {
            this.item = item;
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
