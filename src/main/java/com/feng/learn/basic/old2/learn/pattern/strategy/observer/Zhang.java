/**
 * 
 */
package com.feng.learn.basic.old2.learn.pattern.strategy.observer;

/**
 * @author feng
 *
 */
public class Zhang implements BossArriveListener {

	/* (non-Javadoc)
	 * @see com.feng.learn.pattern.strategy.observer.BossArriveListener#bossArrived()
	 */
	@Override
	public void bossArrived() {
		System.out.println("老板来了，赶紧把视频关掉。");
	}
}
