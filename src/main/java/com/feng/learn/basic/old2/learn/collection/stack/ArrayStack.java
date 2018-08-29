/**
 * 
 */
package com.feng.learn.basic.old2.learn.collection.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author feng
 *
 */
public class ArrayStack<E> implements Stack<E> {

	private static final int DEFAULT_SIZE = 1 << 3;
	private static final int ARRAY_MAX_SIZE = 1 << 31 - 8;

	private E[] table;

	private int size;

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		table = (E[]) new Object[DEFAULT_SIZE];
	}

	/* (non-Javadoc)
	 * @see com.feng.learn.collection.stack.Stack#pop()
	 */
	@Override
	public E pop() {
		if (0 == size)
			return null;
		E e = table[--size];
		table[size] = null;
		return e;
	}

	/* (non-Javadoc)
	 * @see com.feng.learn.collection.stack.Stack#push(java.lang.Object)
	 */
	@Override
	public void push(E e) {
		ensureCapacity(size + 1);
		table[size++] = e;
	}

	/**
	 * @param i
	 */
	private void ensureCapacity(int minCapacity) {
		if (minCapacity > table.length)
			grow(minCapacity);
	}

	/**
	 * @param minCapacity
	 */
	@SuppressWarnings("unchecked")
	private void grow(int minCapacity) {
		int oldCapacity = table.length;
		int newCapacity = oldCapacity + oldCapacity >> 1;
		newCapacity = Math.max(newCapacity, minCapacity);
		if (newCapacity >= ARRAY_MAX_SIZE) {
			newCapacity = hugeCapacity(minCapacity);
		}

		Object[] newTable = new Object[newCapacity];
		System.arraycopy(table, 0, newTable, 0, oldCapacity);
		table = (E[]) newTable;
	}

	/**
	 * @param minCapacity
	 * @return
	 */
	private int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new Error("");
		}
		return minCapacity < ARRAY_MAX_SIZE ? ARRAY_MAX_SIZE : Integer.MAX_VALUE;
	}

	/* (non-Javadoc)
	 * @see com.feng.learn.collection.stack.Stack#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see com.feng.learn.collection.stack.Stack#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return 0 == size;
	}

	/* (non-Javadoc)
	 * @see com.feng.learn.collection.stack.Stack#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<E> {

		private int next = size - 1;
		//private int lastRet = -1;

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return next >= 0;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			if (next < 0)
				throw new NoSuchElementException();
			return table[next--];
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
