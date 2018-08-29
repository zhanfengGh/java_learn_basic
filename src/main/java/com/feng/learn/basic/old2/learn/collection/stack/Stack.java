/**
 * 
 */
package com.feng.learn.basic.old2.learn.collection.stack;

import java.util.Iterator;

/**
 * @author feng
 *
 */
public interface Stack<E> {
	E pop();

	void push(E e);

	int size();

	boolean isEmpty();

	Iterator<E> iterator();
}
