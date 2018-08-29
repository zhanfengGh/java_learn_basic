/**
 * 
 */
package com.feng.learn.basic.old2.learn.collection.list;

import java.util.*;

/**
 * @author feng
 *
 */
public class ArrayList<E> implements List<E> {

	private static final int DEFAULT_CAPACITY = 1 << 3;
	private static final int MAX_CAPACITY = 0x7FFFFFFF; // int 最大值
	private E[] datas;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		datas = (E[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

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
		return indexOf(o) != -1;
	}

	public boolean containsBetter(Object o) {
		return indexOf(o) >= 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		// return new ArrayListIterator();
		return new Itr();
	}

	private class ArrayListIterator implements Iterator<E> {
		private int curPoint = -1;
		private boolean canDelete = false;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return curPoint < size - 1;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			// 重要
			if (curPoint >= size - 1) {
				throw new NoSuchElementException();
			}

			E item = datas[++curPoint];
			canDelete = true;
			return item;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			if (canDelete) {
				ArrayList.this.remove(curPoint--);
				canDelete = false;
			} else {
				throw new IllegalStateException();
			}
		}

	}

	private class Itr implements Iterator<E> {

		private int cursor = 0;
		private int lastRet = -1;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			if (cursor >= size) {
				throw new NoSuchElementException();
			}
			E[] datas = ArrayList.this.datas;
			lastRet = cursor;
			return datas[cursor++];
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			if (-1 == lastRet) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(lastRet);
			cursor = lastRet;
			lastRet = -1;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		System.arraycopy(datas, 0, result, 0, size);
		return result;
	}

	public Object[] toArray2() {
		return Arrays.copyOf(datas, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	// T类型由用户传入，若和E类型不兼容如何处理？
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			a = (T[]) new Object[size];
		} else if (a.length > size) {
			a[size] = null;
		}
		System.arraycopy(datas, 0, a, 0, size);
		return a;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray2(T[] a) {
		if (a.length < size) {
			return (T[]) Arrays.copyOf(datas, size, a.getClass());
		}
		System.arraycopy(datas, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		ensureCapacity(1);
		datas[size++] = e;
		return true;
	}

	private static final int ARRAY_MAX_SIZE = Integer.MAX_VALUE - 8;

	// 优先考虑时间，然后空间
	private void timeFirstGrow(int minCapacity) {
		int oldCapacity = datas.length;
		int newCapacity = oldCapacity + oldCapacity >> 1;
		newCapacity = Math.max(minCapacity, newCapacity);
		if (newCapacity - ARRAY_MAX_SIZE > 0) {
			newCapacity = hugeCapacity(minCapacity);
		}
		datas = Arrays.copyOf(datas, newCapacity);
	}

	// 优先考虑空间，然后时间
	private void spaceFirstGrow(int minCapacity) {
		int oldCapacity = datas.length;
		int capacityNeeded = minCapacity - oldCapacity;
		capacityNeeded = getMinAddCapacity(capacityNeeded);
		int newCapacity = oldCapacity + capacityNeeded;

		// 重要
		// 防止超过int的最大值
		newCapacity = Math.max(minCapacity, newCapacity);

		if (newCapacity > ARRAY_MAX_SIZE) {
			newCapacity = hugeCapacity(minCapacity);
		}
		@SuppressWarnings("unchecked")
		E[] newDatas = (E[]) new Object[newCapacity];
		System.arraycopy(datas, 0, newDatas, 0, oldCapacity);
		datas = newDatas;
	}

	private void ensureCapacityInternal(int minCapacity) {
		if (minCapacity - datas.length > 0) {
			timeFirstGrow(minCapacity);
			spaceFirstGrow(minCapacity);
		}

	}

	/**
	 * @param minCapacity
	 * @return
	 */
	private int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError();
		}
		return minCapacity > ARRAY_MAX_SIZE ? Integer.MAX_VALUE : ARRAY_MAX_SIZE;
	}

	/**
	 * @param capacity
	 * 
	 */
	private void ensureCapacity(int capacityAdded) {
		if (capacityAdded < 0) {
			throw new RuntimeException("");
		}
		int capacityNeeded = size + capacityAdded;
		if (capacityNeeded < 0) {
			throw new RuntimeException("超出Integer的最大值");
		}
		int lng = datas.length;
		int realAdded = capacityNeeded - lng;
		if (realAdded <= 0) {
			return;
		}
		int finalCapacity;
		// 添加的容量为2的倍数
		int capacityRealAdded = getMinAddCapacity(realAdded);

		int capacityAfter = capacityRealAdded + lng;
		if (capacityAfter < 0) {
			finalCapacity = Integer.MAX_VALUE;
		} else {
			finalCapacity = capacityAfter;
		}
		@SuppressWarnings("unchecked")
		E[] newDatas = (E[]) new Object[finalCapacity];
		System.arraycopy(datas, 0, newDatas, 0, lng);
		datas = newDatas;
	}

	/**
	 * @param i
	 * @return
	 */
	private int getMinAddCapacity(int i) {
		if (i % 2 == 0) {
			return i;
		} else {
			return (i >> 1 + 1) << 1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		int firstIndex = indexOf(o);
		if (-1 == firstIndex) {
			return false;
		}
		remove(firstIndex);
		return true;
	}

	public boolean removeBetter(Object o) {
		if (null == o) {
			for (int i = 0; i < size; i++) {
				if (null == datas[i]) {
					fastRemove(i);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(datas[i])) {
					fastRemove(i);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param i
	 */
	private void fastRemove(int index) {
		int numMoved = size - (index + 1);
		if (numMoved > 0) {
			System.arraycopy(datas, index + 1, datas, index, numMoved);
		}
		datas[--size] = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		@SuppressWarnings("unchecked")
		Object[] itemsToAdd = c.toArray();
		int itemsToAddLength = itemsToAdd.length;
		ensureCapacity(itemsToAddLength);
		System.arraycopy(itemsToAdd, 0, datas, size, itemsToAddLength);
		size += itemsToAddLength;
		return true;
	}

	/**
	 * 和上面的方法相比 1》没有转型 2》返回结果
	 * 
	 */
	public boolean addAllBetter(Collection<? extends E> c) {
		Object[] a = c.toArray();
		int l = a.length;
		ensureCapacityInternal(size + l);
		System.arraycopy(a, 0, datas, size, l);
		size += l;
		return 0 != l;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		if (index >= size) {
			addAll(c);
		} else {
			@SuppressWarnings("unchecked")
			E[] itemsToAdd = (E[]) c.toArray();
			int itemsToAddLength = itemsToAdd.length;
			ensureCapacity(itemsToAddLength);

			// 下面是错误的
			// System.arraycopy(datas, index, datas, index + itemsToAddLength,
			// itemsToAddLength);
			System.arraycopy(datas, index, datas, index + itemsToAddLength, size - index);
			System.arraycopy(itemsToAdd, 0, datas, index, itemsToAddLength);
			size += itemsToAddLength;
		}
		return false;
	}

	public boolean addAllBetter(int index, Collection<? extends E> c) {
		checkRangeForAdd(index);
		Object[] a = c.toArray();
		int l = a.length;
		int numMoved = size - index;
		if (numMoved > 0) {
			System.arraycopy(datas, index, datas, index + l, numMoved);
		}
		System.arraycopy(a, 0, datas, index, l);

		size += l;
		return 0 != l;
	}

	/**
	 * @param index
	 */
	private void checkRangeForAdd(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean listChanged = false;
		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (remove(o)) {
				listChanged = true;
			}
		}
		return listChanged;
	}

	public boolean removeAllBetter(Collection<?> c) {
		return batchRemove(c, false);
	}

	/**
	 * 绝妙算法
	 * 
	 * @param c
	 * @param complement
	 *            true 交集；false this - c.
	 * @return
	 */
	private boolean batchRemove(Collection<?> c, boolean complement) {
		final Object[] datas = this.datas;
		int r = 0, w = 0;
		boolean modified = false;
		try {
			for (; r < size; r++) {
				if (c.contains(datas[r]) == complement) {
					datas[w++] = datas[r];
				}
			}
		} finally {
			// 小心contains方法的异常
			if (r != size) {
				int itemLeft = size - r;
				System.arraycopy(datas, r, datas, w, itemLeft);
				w += itemLeft;
			}
			if (w != size) {
				for (int i = w; i < size; i++) {
					datas[i] = null;
				}
				size = w;
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
		return batchRemove(c, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() {
		setElementsToNull(datas);
		size = 0;
	}

	/**
	 * @param datas2
	 */
	private void setElementsToNull(E[] datas) {
		for (E data : datas) {
			data = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#get(int)
	 */
	@Override
	public E get(int index) {
		rangeCheck(index);
		return datas[index];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		E oldItem = datas[index];
		datas[index] = element;
		return oldItem;
	}

	/**
	 * 
	 * @param index
	 */
	private void rangeCheck(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException("Index=" + index + ", Size=" + size);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		ensureCapacity(1);
		if (index >= size) {
			datas[size] = element;
		} else {
			System.arraycopy(datas, index, datas, index + 1, size - index);
			datas[index] = element;
		}
		size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(int)
	 */
	@Override
	public E remove(int index) {
		rangeCheck(index);

		E oldElement = datas[index];

		// 重要，小心内存泄漏
		// 位置错误
		datas[index] = null;

		System.arraycopy(datas, index + 1, datas, index, size - 1 - index);
		size--;
		return oldElement;
	}

	public E removeBetter(int index) {
		rangeCheck(index);
		E item = datas[index];

		int numMoved = size - (index + 1);
		if (numMoved > 0) {
			System.arraycopy(datas, index + 1, datas, index, numMoved);
		}

		// 重要，小心内存泄漏
		// clear to let GC do its work;
		// 错误，位置错误
		// datas[size--] = null;
		datas[--size] = null;

		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		if (null == o) {
			return indexOfNull();
		}
		for (int i = 0; i < size; i++) {
			if (o.equals(datas[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @return
	 */
	private int indexOfNull() {
		for (int i = 0; i < size; i++) {
			if (null == datas[i]) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		if (null == o) {
			return lastIndexOfNull();
		} else {
			for (int i = size - 1; i >= 0; i--) {
				if (o.equals(datas[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * @return
	 */
	private int lastIndexOfNull() {
		for (int i = size - 1; i >= 0; i--) {
			if (null == datas[i]) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
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
