package com.feng.learn.basic.concurrence.maxvalue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author feng_Pc
 * <p>
 * 采用 Lock-Free 算法 来实现线程的同步。。。
 */
public class MaxValue2 {

    private AtomicInteger maxValue = new AtomicInteger();

    public int getMaxValue() {
        return maxValue.get();
    }

    // OK 取值 比较 和赋值 是一个原子操作。
    public void setMaxValue(int maxValue) {
        for (; ; ) {
            int current = this.getMaxValue();
            if (maxValue > current) {
                if (this.maxValue.compareAndSet(current, maxValue)) {
                    break;
                } else {
                    continue;
                }
            } else {
                break;
            }
        }
    }

    // Error 取值 比较 和赋值 不是一个原子操作。
    public void setMaxValueError(int maxValue) {
        int oldValue = this.getMaxValue();
        if (maxValue > oldValue) {
            for (; ; ) {
                if (this.maxValue.compareAndSet(oldValue, maxValue)) {
                    break;
                }
            }
        }
    }

    public void setMaxValue2(int maxVal) {
        for (; ; ) {
            int cur = maxValue.get();
            if (cur >= maxVal) {
                break;
            }
            if (maxValue.compareAndSet(cur, maxVal)) {
                break;
            }
        }
    }

}
