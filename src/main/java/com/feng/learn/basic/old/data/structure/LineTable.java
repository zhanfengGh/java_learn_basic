package com.feng.learn.basic.old.data.structure;

import java.util.Arrays;

public class LineTable implements LineTableI {

	public static final int DEFAULT_CAPACITY = 16;

	public static final int DEFAULT_INCREMENT = 5;

	private int size;

	private Object[] data;

	private void init(int initSize) {
		data = new Object[initSize];
	}

	public LineTable() {
		init(DEFAULT_CAPACITY);
	}

	public LineTable(int initSize) {
		this.init(initSize);
	}

	private boolean outOfCapacity() {
		return size >= data.length;
	}

	@Override
	public boolean add(Object o) {
		if (outOfCapacity()) {
			Object[] data = new Object[this.data.length + DEFAULT_INCREMENT];
			moveDataToNewArray(this.data, 0, size, data, 0);
			this.data = data;
		}
		data[size++] = o;
		return true;
	}

	private void moveDataToNewArray(Object[] src, int srcStart, int srcEnd,
			Object[] dst, int dstStart) {

		for (int i = srcStart; i < srcEnd; i++) {
			dst[dstStart + i] = src[i];
		}
	}

	@Override
	public boolean add(int index, Object o) {
		int arrIndex = index - 1;
		if (arrIndex < size) {
			if (outOfCapacity()) {
				Object[] data = new Object[this.data.length + DEFAULT_INCREMENT];
				moveDataToNewArray(this.data, 0, arrIndex, data, 0);
				data[arrIndex] = o;
				moveDataToNewArray(this.data, arrIndex, size, data, ++arrIndex);
				this.data = data;
				size++;
			} else {
				moveDataToItself(data, arrIndex, size);
				data[arrIndex] = o;
				size++;
			}
		} else {
			add(o);
		}
		return true;
	}

	private void moveDataToItself(Object[] data, int startIndex, int endIndex) {
		for (int index = endIndex - 1; index >= startIndex; index--) {
			data[index + 1] = data[index];
		}
	}

	@Override
	public Object remove() {
		Object result = data[size - 1];
		data[--size] = null;
		return result;
	}

	@Override
	public Object remove(int index) {
		if (index >= 1 && index <= size) {
			Object result = data[index - 1];
			data[index - 1] = null;
			for (int i = index - 1; i < size; i++) {
				data[i] = data[i + 1];
			}
			size -= 1;
			return result;
		}
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Object get(int index) {
		if (index >= 1 && index <= size) {
			return data[index - 1];
		}
		return null;
	}

	@Override
	public int getIndex(Object o) {
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		this.size = 0;
	}

	@Override
	public String toString() {
		return "LineTable [size=" + size + ", data=" + Arrays.toString(data)
				+ "]";
	}

}
