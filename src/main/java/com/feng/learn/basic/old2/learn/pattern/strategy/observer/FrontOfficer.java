/**
 * 
 */
package com.feng.learn.basic.old2.learn.pattern.strategy.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author feng
 *
 */
public class FrontOfficer {

	private List<BossArriveListener> listeners = new LinkedList<BossArriveListener>();

	public void bossAtDoor() {
		for (BossArriveListener l : listeners) {
			l.bossArrived();
		}
	}

	public boolean addListener(BossArriveListener l) {
		return listeners.add(l);
	}

}
