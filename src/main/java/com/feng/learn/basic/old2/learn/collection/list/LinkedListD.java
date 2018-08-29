/**
 * 
 */
package com.feng.learn.basic.old2.learn.collection.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author feng
 *
 */
public class LinkedListD<E> implements List<E> {

	private final Node<E> header = new Node<E>();
	private Node<E> tail = header;
	private int size;

	//双端节点
	private class Node<N> {
		private N item;
		private Node<N> pre;
		private Node<N> next;

		public Node() {
		}

		public Node(N item, Node<N> pre, Node<N> next) {
			this.item = item;
			this.pre = pre;
			this.next = next;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return 0 == size;
	}

	/* (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	/* (non-Javadoc)
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<E> {

		private Node<E> cur = header.next;
		private Node<E> lastRet = header;

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return null != cur;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			if (null != cur) {
				E item = cur.item;
				lastRet = cur;
				cur = cur.next;
				return item;
			} else {
				throw new NoSuchElementException();
			}
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			if (header != lastRet) {
				LinkedListD.this.doRemove(lastRet);
				lastRet = header;
			} else {
				throw new IllegalStateException();
			}
		}

	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		Object[] a = new Object[size];
		Node<E> n = header.next;
		int i = 0;
		while (null != n) {
			a[i++] = n.item;
			n = n.next;
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		Object[] at = null;
		if (a.length < size) {
			at = new Object[size];
		} else {
			at = a;
			if (a.length > size) {
				at[size] = null;
			}
		}
		Node<E> n = header.next;
		int i = 0;
		while (null != n) {
			at[i++] = n.item;
			n = n.next;
		}

		return (T[]) at;
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		addAtTail(e);
		return false;
	}

	private void addAtTail(E e) {
		Node<E> n = new Node<E>(e, tail, null);
		tail.next = n;
		tail = n;
		size += 1;
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		Node<E> n = header.next;
		Node<E> nodeToDel = null;
		if (null == o) {
			while (null != n) {
				if (null == n.item) {
					nodeToDel = n;
					break;
				}
				n = n.next;
			}
		} else {
			while (null != n) {
				if (o.equals(n.item)) {
					nodeToDel = n;
					break;
				}
				n = n.next;
			}
		}
		if (null == nodeToDel) {
			return false;
		} else {
			doRemove(nodeToDel);
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> itr = c.iterator();
		while (itr.hasNext()) {
			if (!contains(itr.next())) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (0 == c.size()) {
			return false;
		} else {
			Iterator<? extends E> itr = c.iterator();
			while (itr.hasNext()) {
				addAtTail(itr.next());
			}
			return true;
		}

	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (0 != c.size()) {
			Iterator<? extends E> itr = c.iterator();
			Node<E> n = nodeOfIndexBetter(index);
			while (itr.hasNext()) {
				linkBefore(itr.next(), n);
			}
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return batchRemove(c, true);
	}

	/**
	 * 
	 * @param c
	 * @param compliment
	 * @return
	 */
	private boolean batchRemove(Collection<?> c, boolean compliment) {
		boolean modified = false;
		Node<E> n = header.next;
		while (null != n) {
			if (c.contains(n.item) == compliment) {
				doRemove(n);
				modified = true;
			}
			n = n.next;
		}
		return modified;
	}

	/* (non-Javadoc)
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return batchRemove(c, false);
	}

	/* (non-Javadoc)
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() {
		Node<E> n = header.next;
		while (null != n) {
			n.pre.next = null;
			n.pre = null;
			n.item = null;
			n = n.next;
		}

		size = 0;
		tail = header;
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override
	public E get(int index) {
		rangeCheckForGet(index);
		return nodeOfIndexBetter(index).item;
	}

	/**
	 * 0 <= index < size
	 * @param index
	 */
	private void rangeCheckForGet(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		rangeCheckForGet(index);
		Node<E> n = nodeOfIndexBetter(index);
		E oldVal = n.item;
		n.item = element;
		return oldVal;
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		if (size == index) {
			addAtTail(element);
		} else {
			rangeCheck(index);
			Node<E> n = nodeOfIndex(index);
			Node<E> nodeToAdd = new Node<E>(element, n.pre, n);
			n.pre.next = nodeToAdd;
			n.pre = nodeToAdd;
			size += 1;
		}

	}

	private void addBetter(int index, E e) {
		if (size == index) {
			addAtTail(e);
		} else {
			rangeCheck(index);
			linkBefore(e, nodeOfIndexBetter(index));
		}
	}

	/**
	 * @param e
	 * @param nodeOfIndexBetter
	 */
	private void linkBefore(E e, LinkedListD<E>.Node<E> node) {
		Node<E> pre = node.pre;
		Node<E> n = new Node<E>(e, pre, node);
		pre.next = n;
		node.pre = n;
		size += 1;
	}

	private Node<E> nodeOfIndexBetter(int index) {
		Node<E> n = null;
		if (index < size >> 1) {
			// 从前向后查
			n = header.next;
			int ptr = 0;
			while (ptr++ != index) {
				n = n.next;
			}
		} else {
			// 从后向前查
			n = tail;
			int ptr = size;
			while (ptr-- != index) {
				n = n.pre;
			}
		}
		return n;
	}

	/**
	 * @param index
	 * @return
	 */
	private LinkedListD<E>.Node<E> nodeOfIndex(int index) {
		Node<E> n = header.next;
		int count = 0;
		while (count++ != index) {
			n = n.next;
		}
		return n;
	}

	private void rangeCheck(int index) {
		if (!isPositionIndex(index)) {
			throw new IndexOutOfBoundsException();
		}
	}

	private boolean isPositionIndex(int pos) {
		return pos >= 0 && pos <= size;
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	@Override
	public E remove(int index) {
		rangeCheckForGet(index);
		Node<E> n = nodeOfIndexBetter(index);
		E e = n.item;
		doRemove(n);
		return e;
	}

	/**
	 * @param n
	 */
	private void doRemove(LinkedListD<E>.Node<E> n) {
		if (tail == n) {
			tail = n.pre;
			tail.next = null;
		} else {
			n.pre.next = n.next;
			n.next.pre = n.pre;
		}

		n.item = null;
		n.pre = null;
		n.next = null;
		size--;
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		int index = 0;
		if (null == o) {
			for (Node<E> n = header.next; null != n; n = n.next) {
				if (null == n.item) {
					return index;
				}
				index++;
			}
		} else {
			for (Node<E> n = header.next; null != n; n = n.next) {
				if (o.equals(n.item)) {
					return index;
				}
				index++;
			}
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		int index = size - 1;
		if (o == null) {
			for (Node<E> n = tail; header != n; n = n.pre) {
				if (null == n.item) {
					return index;
				}
				index--;
			}
		} else {
			for (Node<E> n = tail; header != n; n = n.pre) {
				if (o.equals(n.item)) {
					return index;
				}
				index--;
			}
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public java.util.List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
