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
public class LinkedList<E> implements List<E> {

	private class Node<D> {
		private D item;
		private Node<D> next;

		public Node() {
		};

		public Node(D item, Node<D> next) {
			this.item = item;
			this.next = next;
		}

	}

	private final Node<E> head = new Node<E>();
	private Node<E> tail = head;

	private int size;

	public LinkedList() {
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return 0 == size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<E> {

		private Node<E> cursor = head;
		private Node<E> nodePreLastRet = head;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return null != cursor.next;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			Node<E> nodeToRet = cursor.next;
			if (null == nodeToRet) {
				throw new NoSuchElementException();
			}
			E e = nodeToRet.item;
			nodePreLastRet = cursor;
			cursor = cursor.next;
			return e;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			if (head == nodePreLastRet) {
				throw new IllegalStateException();
			} else {
				Node<E> lastRet = nodePreLastRet.next;

				nodePreLastRet.next = lastRet.next;
				size -= 1;

				if (tail == lastRet) {
					tail = nodePreLastRet;
				}

				// 小心内存泄漏
				lastRet.item = null;
				lastRet.next = null;

				nodePreLastRet = head;

			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@Override
	public <E> E[] toArray(E[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		tail.next = new Node<E>(e, null);
		tail = tail.next;
		size++;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		Node<E> ptr = preNodeForObject(o);
		if (null == ptr) {
			return false;
		} else {
			doRemove(ptr);
			return true;
		}
	}

	private E doRemove(Node<E> pre) {
		Node<E> nodeToDel = pre.next;
		E item = nodeToDel.item;

		pre.next = nodeToDel.next;
		size -= 1;

		if (nodeToDel == tail) {
			tail = pre;
		}

		nodeToDel.item = null;
		nodeToDel.next = null;
		return item;
	}

	private Node<E> preNodeForObject(Object o) {
		Node<E> pre = head;
		Node<E> cur = null;
		if (null == o) {
			while ((cur = pre.next) != null) {
				if (null == cur.item) {
					return pre;
				}
				pre = pre.next;
			}
			/*
			for (Node<E> ptr = head; ptr.next != null; ptr = ptr.next) {
				if (null == ptr.next.item) {
					return ptr;
				}
			}
			*/

		} else {
			while ((cur = pre.next) != null) {
				if (o.equals(cur.item)) {
					return pre;
				}
				pre = pre.next;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		Iterator<? extends E> itr = c.iterator();
		while (itr.hasNext()) {
			add(itr.next());
		}
		return c.size() != 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		rangeCheck(index);
		Iterator<? extends E> itr = c.iterator();
		if (itr.hasNext()) {
			Node<E> head = new Node<E>();
			Node<E> tail = head;
			head.next = tail = new Node<E>(itr.next(), null);
			while (itr.hasNext()) {
				tail.next = new Node<E>(itr.next(), null);
				tail = tail.next;
			}
			Node<E> ptr = preNodeForIndex(index);
			tail.next = ptr.next;
			ptr.next = head.next;
			size += c.size();
			return true;
		} else {
			return false;
		}
	}

	private Node<E> preNodeForIndex(int index) {
		Node<E> cursor = head;
		int count = 0;
		while (count++ != index) {
			cursor = cursor.next;
		}
		return cursor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		Iterator<?> itr = c.iterator();
		boolean modified = false;
		while (itr.hasNext()) {
			if (remove(itr.next())) {
				modified = true;
			}
		}
		return modified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		Node<E> pre = head;
		Node<E> cursor = null;
		boolean modified = false;
		while (null != (cursor = pre.next)) {
			if (!c.contains(cursor.item)) {
				doRemove(pre);
				modified = true;
			}
		}
		return modified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#clear()
	 */
	// 小心内存泄漏
	@Override
	public void clear() {
		Node<E> cursor = head.next;
		while (null != cursor) {

			Node<E> nodeToClear = cursor;
			cursor = cursor.next;

			// clear to let GC do it's work
			nodeToClear.item = null;
			nodeToClear.next = null;
		}
		size = 0;
		tail = head;
		head.next = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#get(int)
	 */
	@Override
	public E get(int index) {
		rangeCheck(index);
		Node<E> ptr = preNodeForIndex(index);
		return ptr.next.item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		Node<E> node = preNodeForIndex(index).next;
		E oldVal = node.item;
		node.item = element;
		return oldVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		rangeCheck(index);
		Node<E> cursor = preNodeForIndex(index);
		cursor.next = new Node<E>(element, cursor.next);
		size++;
	}

	/**
	 * @param index
	 */
	// 不能插在最后，否者tail指针会出现异常
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(int)
	 */
	@Override
	public E remove(int index) {
		rangeCheck(index);
		Node<E> ptr = preNodeForIndex(index);
		return doRemove(ptr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		return indexOf(o, false);
	}

	private int indexOf(Object o, boolean last) {
		int count = 0;
		Node<E> cursor = head.next;
		if (false == last) {
			// 从前向后搜索
			if (null == o) {
				for (; null != cursor; count += 1, cursor = cursor.next) {
					if (null == (cursor.item)) {
						return count;
					}
				}
			} else {
				for (; null != cursor; count += 1, cursor = cursor.next) {
					if (o.equals(cursor.item)) {
						return count;
					}
				}
			}
			return -1;
		} else {
			// 从后向前搜索
			int index = -1;
			if (null == o) {
				for (; null != cursor; count += 1, cursor = cursor.next) {
					if (null == (cursor.item)) {
						index = count;
					}
				}
			} else {
				for (; null != cursor; count += 1, cursor = cursor.next) {
					if (o.equals(cursor.item)) {
						index = count;
					}
				}
			}
			return index;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		return indexOf(o, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public java.util.List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
