/**
 * 
 */
package com.feng.learn.basic.old2.learn.collection.mapBetter;

import java.util.*;

/**
 * @author feng
 *
 */
public class HashMap<K, V> implements Map<K, V> {

	static final int DEFAULT_INIT_SIZE = 1 << 4;
	static final double DEFAULT_INIT_FACTOR = 0.75;
	static final int MAP_MAX_CAPACITY = 1 << 30;

	private Entry<K, V>[] table;
	private int size;
	private int threshold;
	private double factor;

	/* 遍历器不能缓存
	private volatile ValueItr valueItr;
	private volatile KeyItr keyItr;
	private volatile EntryItr entryItr;
	*/
	private EntrySet entrySet;
	private KeySet keySet;

	@SuppressWarnings("unchecked")
	public HashMap() {
		factor = DEFAULT_INIT_FACTOR;
		threshold = (int) (DEFAULT_INIT_SIZE * DEFAULT_INIT_FACTOR);
		table = (Entry<K, V>[]) new Object[DEFAULT_INIT_SIZE];
	}

	ValueItr newValueItr() {
		return new ValueItr();
	}

	KeyItr newKeyItr() {
		return new KeyItr();
	}

	EntryItr newEntryItr() {
		return new EntryItr();
	}

	private class ValueItr extends HashItr<V> {

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public V next() {
			return nextEntry().value;
		}

	}

	private class KeyItr extends HashItr<K> {

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public K next() {
			return nextEntry().key;
		}

	}

	private class EntryItr extends HashItr<Map.Entry<K, V>> {

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Entry<K, V> next() {
			return nextEntry();
		}

	}

	private abstract class HashItr<R> implements Iterator<R> {
		int index;
		Entry<K, V> next;
		Entry<K, V> lastRet;

		public HashItr() {
			index = 0;
			while (index < size && null == (next = table[index++]))
				;
			lastRet = null;

			/*
			for (Entry<K, V> e : table) {
				if (null != e) {
					next = e;
					break;
				}
			}
			*/
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return null != next;
		}

		final Entry<K, V> nextEntry() {
			if (null == next) {
				throw new NoSuchElementException();
			}
			lastRet = next;
			// next = ?
			next = next.next;
			if (null == next) {
				while (index < size && null == (next = table[index++]))
					;
			}
			return lastRet;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			if (null == lastRet) {
				throw new IllegalStateException();
			}
			HashMap.this.removeEntry(lastRet.key);
			lastRet = null;
		}

	}

	static class Entry<K, V> implements Map.Entry<K, V> {

		int hash;
		K key;
		V value;
		Entry<K, V> next;

		Entry(int h, K k, V v, Entry<K, V> n) {
			hash = h;
			key = k;
			value = v;
			next = n;
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
			V oldVal = value;
			this.value = value;
			return oldVal;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + hash;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entry other = (Entry) obj;
			if (hash != other.hash)
				return false;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

	}

	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		return null != getEntry(key);
	}

	Entry<K, V> getEntry(Object key) {
		int hash = hash(key);
		for (Entry<K, V> e = table[indexFor(hash, table.length)]; null != e; e = e.next) {
			Object k;
			if (hash == e.hash && (key == (k = e.key) || (null != key && key.equals(k)))) {
				return e;
			}
		}
		return null;
	}

	/**
	 *
	 * @param key
	 */
	boolean removeEntry(Object key) {
		Entry<K, V> pre = null;
		int hash = hash(key);
		int index = indexFor(hash, table.length);
		for (Entry<K, V> e = table[index]; null != e; pre = e, e = e.next) {
			Object k;
			if (hash == e.hash && (key == (k = e.key) || (null != key && key.equals(k)))) {
				if (null == pre) {
					table[index] = e.next;
				} else {
					pre.next = e.next;
				}
				size--;
				e.key = null;
				e.value = null;
				e.next = null;
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		ValueItr valueItr = new ValueItr();
		if (null != value) {
			while (valueItr.hasNext()) {
				if (value.equals(valueItr.next())) {
					return true;
				}
			}
		} else {
			while (valueItr.hasNext()) {
				if (null == valueItr.next()) {
					return true;
				}
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public V get(Object key) {
		Entry<K, V> e = getEntry(key);
		return null == e ? null : e.value;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value) {
		if (null == key) {
			return putForNull(value);
		}

		int hash = hash(key);
		int index = indexFor(hash, table.length);

		// 先搜索后添加
		for (Entry<K, V> e = table[index]; null != e; e = e.next) {
			Object k;
			if (hash == e.hash && ((k = e.key) == key || key.equals(k))) {
				V oldVal = e.value;
				e.value = value;
				return oldVal;
			}
		}

		addEntry(hash, key, value, index);
		return null;
	}

	/**
	 * @param value
	 * @return
	 */
	private V putForNull(V value) {
		for (Entry<K, V> e = table[0]; null != e; e = e.next) {
			if (e.hash == 0 && null == e.key) {
				V oldVal = e.value;
				e.value = value;
				return oldVal;
			}
		}
		addEntry(0, null, value, 0);
		return null;
	}

	/**
	 * @param hash
	 * @param key
	 * @param value
	 * @param index
	 */
	void addEntry(int hash, K key, V value, int index) {
		Entry<K, V> e = table[index];
		if (size >= threshold && null != e) {
			resize(2 * table.length);
		}
		hash = hash(key);
		index = indexFor(hash, table.length);
		createEntry(hash, key, value, index);
	}

	/**
	 * @param i
	 */
	private void resize(int newCapacity) {
		if (table.length == MAP_MAX_CAPACITY) {
			threshold = Integer.MAX_VALUE;
			return;
		}

		@SuppressWarnings("unchecked")
		Entry<K, V>[] newTable = (Entry<K, V>[]) new Object[newCapacity];
		transferTo(newTable);
		table = newTable;
	}

	/**
	 * 数组内存回收问题，小心内存泄漏
	 * @param newTable
	 */
	private void transferTo(Entry<K, V>[] newTable) {
		int newCapacity = newTable.length;
		for (Entry<K, V> e : table) {
			while (null != e) {
				Entry<K, V> next = e.next;

				int hash = hash(e.key);
				int index = indexFor(hash, newCapacity);

				e.next = newTable[index];
				newTable[index] = e;

				e = next;
			}
		}
	}

	/**
	 * @param hash
	 * @param key
	 * @param value
	 * @param index
	 */
	private void createEntry(int hash, K key, V value, int index) {
		Entry<K, V> e = new Entry<K, V>(hash, key, value, table[index]);
		table[index] = e;
		size++;
	}

	/**
	 * @param hash
	 * @param length
	 * @return
	 */
	private int indexFor(int hash, int length) {
		return hash % length;
	}

	/**
	 * @param key
	 * @return
	 */
	private int hash(Object key) {
		return null == key ? 0 : key.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key) {
		Entry<K, V> e = getEntry(key);
		if (null != e) {
			V oldVal = e.getValue();
			removeEntry(key);
			return oldVal;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		/*
		Iterator<?> itr = m.entrySet().iterator();
		while (itr.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<K, V> e = (java.util.Map.Entry<K, V>) itr.next();
			put(e.getKey(), e.getValue());
		}
		*/
		int numToAdd = m.size() + size();
		if (numToAdd > threshold) {
			int capacityNeeded = (int) (numToAdd / factor + 1);
			int capacityToUse = table.length;
			if (capacityNeeded >= MAP_MAX_CAPACITY) {
				capacityToUse = MAP_MAX_CAPACITY;
			} else {
				while (capacityToUse < capacityNeeded) {
					capacityToUse <<= 1;
				}
			}
			if (capacityToUse > table.length)
				resize(capacityToUse);
		}

		for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
			put(e.getKey(), e.getValue());
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(table, null);
		size = 0;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<K> keySet() {
		if (null == keySet) {
			keySet = new KeySet();
		}
		return keySet;
	}

	private class KeySet implements Set<K> {

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
			return null != HashMap.this.getEntry(o);
		}

		/* (non-Javadoc)
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<K> iterator() {
			return newKeyItr();
		}

		/* (non-Javadoc)
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			Object[] a = new Object[size];
			int i = 0;
			for (Iterator<K> itr = iterator(); itr.hasNext();) {
				a[i++] = itr.next();
			}
			return a;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@SuppressWarnings("unchecked")
		@Override
		public <T> T[] toArray(T[] a) {
			if (size > a.length) {
				return (T[]) toArray();
			} else {
				int i = 0;
				Object[] other = a;
				for (Iterator<K> itr = iterator(); itr.hasNext();) {
					other[i++] = itr.next();
				}
				if (a.length > size) {
					a[size] = null;
				}
			}
			return a;
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
			return HashMap.this.removeEntry(o);
		}

		/* (non-Javadoc)
		 * @see java.util.Set#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			for (Iterator<?> itr = c.iterator(); itr.hasNext();) {
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
			boolean modified = false;
			for (Iterator<K> itr = iterator(); itr.hasNext();) {
				if (!c.contains(itr.next())) {
					itr.remove();
					modified = true;
				}
			}

			return modified;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			boolean modified = false;
			for (Iterator<?> itr = c.iterator(); itr.hasNext();) {
				if (remove(itr.next())) {
					modified = true;
				}
			}
			return modified;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			HashMap.this.clear();
		}

	}

	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	private class ValuesCollection implements Collection<V> {

		/* (non-Javadoc)
		 * @see java.util.Collection#size()
		 */
		@Override
		public int size() {
			return size;
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return 0 == size();
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			for (Iterator<V> itr = iterator(); itr.hasNext();) {
				Object v;
				if ((v = itr.next()) == o || (null != o && o.equals(v))) {
					return true;
				}
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#iterator()
		 */
		@Override
		public Iterator<V> iterator() {
			return newValueItr();
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#toArray()
		 */
		@Override
		public Object[] toArray() {
			if (isEmpty()) {
				return new Object[0];
			}
			Object[] a = new Object[size];
			int i = 0;
			for (Iterator<V> itr = iterator(); itr.hasNext();) {
				a[i++] = itr.next();
			}
			return a;
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return null;
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#add(java.lang.Object)
		 */
		@Override
		public boolean add(V e) {
			throw new UnsupportedOperationException();
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			for (Iterator<V> itr = iterator(); itr.hasNext();) {
				Object v = itr.next();
				if (v == o || (null != o && o.equals(v))) {
					itr.remove();
					return true;
				}
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return false;
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends V> c) {
			throw new UnsupportedOperationException();
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			boolean modified = false;
			for (Iterator<V> itr = iterator(); itr.hasNext();) {
				if (c.contains(itr.next())) {
					itr.remove();
					modified = true;
				}
			}
			return modified;
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			boolean modified = false;
			for (Iterator<V> itr = iterator(); itr.hasNext();) {
				if (!c.contains(itr.next())) {
					itr.remove();
					modified = true;
				}
			}
			return modified;
		}

		/* (non-Javadoc)
		 * @see java.util.Collection#clear()
		 */
		@Override
		public void clear() {
			HashMap.this.clear();
		}

	}

	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return newEntrySet();
	}

	/**
	 * @return
	 */
	private Set<Map.Entry<K, V>> newEntrySet() {
		if (null == entrySet) {
			entrySet = new EntrySet();
		}
		return entrySet;
	}

	private class EntrySet implements Set<Map.Entry<K, V>> {

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
		// 比默认版本好
		@Override
		public boolean contains(Object o) {
			if (!(o instanceof Entry)) {
				return false;
			}
			Entry<?, ?> e = (Entry<?, ?>) o;
			Entry<K, V> entry = getEntry(e.getKey());
			if (null == entry) {
				return false;
			} else {
				Object ov = e.getValue();
				V nv = entry.getValue();
				if (o == entry || (ov == nv || (null != ov && ov.equals(nv)))) {
					return true;
				}
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#iterator()
		 */
		@Override
		public Iterator<Map.Entry<K, V>> iterator() {
			return HashMap.this.newEntryItr();
		}

		/* (non-Javadoc)
		 * @see java.util.Set#toArray()
		 */
		@Override
		public Object[] toArray() {
			Object[] a = new Object[size];
			Iterator<Map.Entry<K, V>> itr = iterator();
			for (int i = 0; itr.hasNext();) {
				a[i++] = itr.next();
			}
			return a;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#toArray(java.lang.Object[])
		 */
		@SuppressWarnings("unchecked")
		@Override
		public <T> T[] toArray(T[] a) {
			if (size > a.length) {
				return (T[]) toArray();
			} else {
				Object[] other = a;
				Iterator<Map.Entry<K, V>> itr = iterator();
				for (int i = 0; itr.hasNext();) {
					other[i++] = itr.next();
				}
				if (a.length > size) {
					a[size] = null;
				}
				return a;
			}
		}

		/* (non-Javadoc)
		 * @see java.util.Set#add(java.lang.Object)
		 */
		@Override
		public boolean add(Map.Entry<K, V> e) {
			throw new UnsupportedOperationException();
		}

		/* (non-Javadoc)
		 * @see java.util.Set#remove(java.lang.Object)
		 */
		@Override

		//TODO 可优化
		public boolean remove(Object o) {
			if (!(o instanceof Map.Entry)) {
				return false;
			}
			for (Iterator<Map.Entry<K, V>> itr = iterator(); itr.hasNext();) {
				if (itr.next().equals(o)) {
					itr.remove();
					return true;
				}
			}
			return false;
		}

		/**
		 * 和上面的方法相比不用遍历整个Map
		 * @param o
		 * @return
		 */
		@SuppressWarnings("unused")
		public boolean removeBetter(Object o) {
			if (!(o instanceof Map.Entry)) {
				return false;
			}
			Entry<?, ?> other = (Entry<?, ?>) o;
			Entry<K, V> candidate = getEntry(other.getKey());
			// 小心条件陷阱
			if (null != candidate && candidate.equals(o)) {
				removeEntry(other.getKey());
				return true;
			}
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
		public boolean addAll(Collection<? extends Map.Entry<K, V>> c) {
			throw new UnsupportedOperationException();
		}

		/* (non-Javadoc)
		 * @see java.util.Set#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			return batchRemove(c, false);
		}

		/**
		 *
		 * @param c
		 * @param compliment
		 * @return
		 */
		//TODO 可优化
		// Set 的contains操作是不用遍历的
		private boolean batchRemove(Collection<?> c, boolean compliment) {
			boolean modified = false;
			for (Iterator<Map.Entry<K, V>> itr = iterator(); itr.hasNext();) {
				if (c.contains(itr.next()) == compliment) {
					itr.remove();
					modified = true;
				}
			}
			return modified;
		}

		private boolean batchRemoveBetter(Collection<?> c, boolean compliment) {
			boolean modified = false;
			if (true == compliment) { //removeAll

				// 遍历c
				for (Iterator<?> itr = c.iterator(); itr.hasNext();) {
					Object o = itr.next();
					if (o instanceof Map.Entry) {
						Object k = ((Map.Entry<?, ?>) o).getKey();
						if (contains(o) && HashMap.this.removeEntry(k)) {
							modified = true;
						}
					}
				}

				/* 下面的算法是错误的 注意遍历器
				for (Iterator<?> itr = c.iterator(); itr.hasNext();) {
					if (contains(itr.next())) {
						itr.remove();
						modified = true;
					}
				}
				*/
			} else { //retainAll
				// 必须遍历Map的底层
				for (Iterator<Map.Entry<K, V>> itr = iterator(); itr.hasNext();) {
					if (!c.contains(itr.next())) {
						itr.remove();
					}
				}
			}

			return modified;
		}

		/* (non-Javadoc)
		 * @see java.util.Set#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			return batchRemoveBetter(c, true);
		}

		/* (non-Javadoc)
		 * @see java.util.Set#clear()
		 */
		@Override
		public void clear() {
			HashMap.this.clear();
		}

	}

}
