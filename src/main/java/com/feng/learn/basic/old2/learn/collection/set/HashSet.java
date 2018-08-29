/**
 * 
 */
package com.feng.learn.basic.old2.learn.collection.set;


import com.feng.learn.basic.old2.learn.collection.mapBetter.HashMap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * @author feng
 *
 */
public class HashSet<E> implements Set<E> {

	private static final Object PRESENT = new Object();

	private Map<E, Object> datas = new HashMap<E, Object>();

	/* (non-Javadoc)
	 * @see java.util.Set#size()
	 */
	@Override
	public int size() {
		return datas.size();
	}

	/* (non-Javadoc)
	 * @see java.util.Set#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return 0 == size();
	}

	/* (non-Javadoc)
	 * @see java.util.Set#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return datas.containsKey(o);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return datas.keySet().iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.Set#toArray()
	 */
	@Override
	public Object[] toArray() {
		return datas.keySet().toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.Set#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return datas.keySet().toArray(a);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#add(java.lang.Object)
	 */
	@Override
	public boolean add(E e) {
		return datas.put(e, PRESENT) != PRESENT;
	}

	/* (non-Javadoc)
	 * @see java.util.Set#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		return datas.remove(o) == PRESENT;
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
	public boolean addAll(Collection<? extends E> c) {
		boolean modified = false;
		Iterator<? extends E> itr = c.iterator();
		while (itr.hasNext()) {
			if (add(itr.next())) {
				modified = true;
			}
		}
		return modified;
	}

	/* (non-Javadoc)
	 * @see java.util.Set#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return batchRemove(c, false);
	}

	private boolean batchRemove(Collection<?> c, boolean compliment) {
		boolean modified = false;
		for (Iterator<?> itr = c.iterator(); itr.hasNext();) {
			Object o = itr.next();
			if (contains(o) == compliment && remove(o)) {
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
		return batchRemove(c, true);
	}

	/* (non-Javadoc)
	 * @see java.util.Set#clear()
	 */
	@Override
	public void clear() {
		datas.clear();
	}

}
