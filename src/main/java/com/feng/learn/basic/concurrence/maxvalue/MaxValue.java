package com.feng.learn.basic.concurrence.maxvalue;

/**
 * 这个实现版本有问题，读写内部状态（属性的值）都需要加锁
 */
public class MaxValue {
	
	private int maxValue;

	public int getMaxValue() {
		return maxValue;
	}

	public synchronized void setMaxValue(int maxValue) {
		int oldValue=this.maxValue;
		if (maxValue>oldValue)
			this.maxValue = maxValue;
	}

}
