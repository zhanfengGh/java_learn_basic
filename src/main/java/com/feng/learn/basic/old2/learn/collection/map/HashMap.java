/**
 * 
 */
package com.feng.learn.basic.old2.learn.collection.map;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author feng
 *
 */
public class HashMap<K, V> implements Map<K, V> {

	private static final int DEFAULT_CAPACITY = 1 << 4;
	private static final double DEFAULT_FACTOR = 0.75;

	private Entry[] datas;
	private int capacity;
	private int size;
	private int threshold;

	private double factor;

	@SuppressWarnings("unchecked")
	public HashMap() {
		this.capacity = DEFAULT_CAPACITY;
		this.datas = (HashMap<K, V>.Entry[]) new Object[capacity];
		this.factor = DEFAULT_FACTOR;
		this.threshold = (int) (capacity * factor);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return 0 == size;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		if (null == key) {
			return null == searchForNullKey();
		} else {
			int h = hash(key);
			int index = indexForHash(h);
			return null == searchKey(h, key, index);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		if (null == value) {
			for (int i = 0; i < datas.length; i++) {
				Entry e = datas[i];
				while (null != e) {
					if (null == e.value) {
						return true;
					}
					e = e.next;
				}
			}
			return false;
		} else {
			for (int i = 0; i < datas.length; i++) {
				Entry e = datas[i];
				while (null != e) {
					if (value.equals(e.value)) {
						return true;
					}
					e = e.next;
				}
			}
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public V get(Object key) {
		Entry e = null;
		if (null == key) {
			e = searchForNullKey();
		} else {
			int h = hash(key);
			int index = indexForHash(h);
			e = searchKey(h, key, index);
		}
		return null == e ? null : e.value;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value) {
		if (null == key) {
			return putForNull(value);
		} else {
			int h = hash(key);
			int index = indexForHash(h);
			if (size >= threshold && null != datas[index]) {
				reHashMap(size + 1);
				index = indexForHash(h);
			}
			return doPut(h, key, value, index);
		}
	}

	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	private void reHashMap(int minCapacity) {
		this.capacity = calCapacity(minCapacity);
		this.threshold = (int) (capacity * factor);

		Entry[] oldDatas = this.datas;

		this.datas = (HashMap<K, V>.Entry[]) new Object[this.capacity];
		// 重要
		this.size = 0;

		for (int i = 0; i < oldDatas.length; i++) {
			Entry e = oldDatas[i];

			while (null != e) {
				Entry next = e.next;

				if (null == e.key) {
					putForNull(e.value);
				} else {
					int h = e.h;
					int index = indexForHash(h);
					doPut(h, e.key, e.value, index);
				}

				// clear to let GC do its job
				e.key = null;
				e.value = null;
				e.next = null;

				e = next;
			}

			// clear to let GC do its job;
			oldDatas[i] = null;
		}
	}

	/**
	 * @param minCapacity
	 * @return
	 */
	private int calCapacity(int minCapacity) {
		int newCapacity = this.capacity;
		do {
			newCapacity = newCapacity * 2;
		} while (newCapacity > minCapacity);
		return newCapacity;
	}

	/**
	 * @param value
	 * @return
	 */
	private V putForNull(V value) {
		Entry e = searchForNullKey();
		if (null == e) {
			doAddEntry(0, null, value, 0);
			return null;
		} else {
			V oldVal = e.value;
			e.value = value;
			return oldVal;
		}
	}

	/**
	 * @return
	 */
	private HashMap<K, V>.Entry searchForNullKey() {
		Entry e = datas[0];
		while (null != e) {
			if (0 == e.h && null == e.key) {
				return e;
			}
			e = e.next;
		}
		return null;
	}

	/**
	 * @param h
	 * @param key
	 * @param value
	 * @param index
	 */
	private V doPut(int h, K key, V value, int index) {
		Entry k = searchKey(h, key, index);
		if (null == k) {
			doAddEntry(h, key, value, index);
			return null;
		} else {
			V oldVal = k.value;
			k.value = value;
			return oldVal;
		}
	}

	/**
	 *
	 * @param h
	 * @param key
	 * @param value
	 * @param index
	 */
	private void doAddEntry(int h, K key, V value, int index) {
		datas[index] = new Entry(h, key, value, datas[index]);
		size++;
	}

	/**
	 * @param key
	 * @param index
	 * @return
	 */
	private HashMap<K, V>.Entry searchKey(int hash, Object key, int index) {
		Entry e = datas[index];
		while (null != e) {
			if (hash == e.h && key.equals(e.key)) {
				return e;
			}
			e = e.next;
		}
		return null;
	}

	/**
	 * @param h
	 * @return
	 */
	private int indexForHash(int h) {
		return h % capacity;
	}

	/**
	 * @param key
	 * @return
	 */
	private int hash(Object key) {
		return key.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key) {
		if (null == key) {
			return doRemoveForNull();
		} else {
			return doRemove(key);
		}
	}

	/**
	 * @param key
	 * @return
	 */
	private V doRemove(Object key) {
		int h = hash(key);
		int index = indexForHash(h);
		Entry e = datas[index];
		if (null == e) {
			return null;
		}
		if (key.equals(e.key)) {
			datas[index] = e.next;
			size--;
			V v = e.value;

			e.key = null;
			e.value = null;
			e.next = null;

			return v;
		}
		Entry cur;
		while (null != (cur = e.next)) {
			if (key.equals(cur.key)) {
				e.next = cur.next;
				size--;
				V val = cur.value;

				cur.key = null;
				cur.value = null;
				cur.next = null;

				return val;
			}
			e = e.next;
		}
		return null;
	}

	/**
	 * @return
	 */
	private V doRemoveForNull() {
		Entry e = datas[0];
		if (null == e) {
			return null;
		}
		if (null == e.key) {
			datas[0] = e.next;
			size--;
			V val = e.value;

			e.key = null;
			e.value = null;
			e.next = null;

			return val;
		}

		Entry cur = null;
		while (null != (cur = e.next)) {
			if (null == cur.key) {
				e.next = cur.next;
				size--;
				V val = cur.value;

				cur.key = null;
				cur.value = null;
				cur.next = null;

				return val;
			}
			e = e.next;
		}
		return null;
	}

	/**
	 * 
	 * @param e
	 */
	private void clearEntry(Entry e) {
		e.key = null;
		e.value = null;
		e.next = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(java.util.Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {

		for (int i = 0; i < datas.length; i++) {
			Entry e = datas[i];

			while (null != e) {
				Entry next = e.next;
				e.key = null;
				e.value = null;
				e.next = null;
				e = next;
			}

			datas[i] = null;
		}

		size = 0;
	}

	private class EntryIterator implements Iterator<Entry> {
		private int index = 0;
		private Entry e = null;
		private Entry lastRet = null;

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return !(index == datas.length && null == e);
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Entry next() {

			while (index < HashMap.this.capacity || null != e) {
				if (null == e) {
					e = datas[index++];
				} else {
					lastRet = e;
					K k = e.key;
					e = e.next;
					//return k;
				}
			}
			throw new NoSuchElementException();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			if (null != lastRet) {
				Entry ptr = datas[index - 1];
				if (ptr == lastRet) {
					datas[index - 1].next = ptr.next;
				} else {
					Entry next = null;
					while ((next = ptr.next) != lastRet) {
						ptr = next;
					}
					ptr.next = lastRet.next;
				}

				lastRet.key = null;
				lastRet.value = null;
				lastRet.next = null;

				size--;
				lastRet = null;
			}
			throw new IllegalStateException();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	private class HSet implements Set<K> {

		/* (non-Javadoc)
		 * @see java.util.Set#size()
		 */
		@Override
		public int size() {
			return size;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return 0 == size;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return HashMap.this.containsKey(o);
		}

		/* (non-Javadoc)
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<K> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(K e) {
			throw new UnsupportedOperationException();
		}

		/* (non-Javadoc)
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#containsAll(java.util.Collection)
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
		 * @see java.util.Set#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends K> c) {
			throw new UnsupportedOperationException();
		}

		/* (non-Javadoc)
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			HashMap.this.clear();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	private class Entry implements Map.Entry<K, V> {

		private K key;
		private V value;
		private int h;
		private Entry next;

		public Entry(int h, K key, V value, Entry next) {
			this.h = h;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		/* (non-Javadoc)
		 * @see java.util.Map.Entry#getKey()
		 */
		@Override
		public K getKey() {
			return key;
		}

		/* (non-Javadoc)
		 * @see java.util.Map.Entry#getValue()
		 */
		@Override
		public V getValue() {
			return value;
		}

		/* (non-Javadoc)
		 * @see java.util.Map.Entry#setValue(java.lang.Object)
		 */
		@Override
		public V setValue(V value) {
			V oldVal = this.value;
			this.value = value;
			return oldVal;
		}

	}

}
