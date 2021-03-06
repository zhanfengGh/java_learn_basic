package com.feng.learn.basic.thread.split;

import com.feng.learn.basic.thread.annotation.ThreadSafe;

/**  
 * 此类是线程安全的，但并不能保证语义的正确性
 * @author zhangzhanfeng 
 * @date Dec 10, 2017   
 */
@ThreadSafe(authors = { "zhanfeng.zhang" })
public class Location {
	private double x_, y_;

	public Location(double x, double y) {
		x_ = x;
		y_ = y;
	}

	public synchronized double x() {
		return x_;
	}

	public synchronized double y() {
		return y_;
	}

	public synchronized void moveBy(double dx, double dy) {
		x_ = x_ + dx;
		y_ = y_ + dy;
	}

}
