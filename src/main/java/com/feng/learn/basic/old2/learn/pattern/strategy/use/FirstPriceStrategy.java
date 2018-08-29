/**
 * 
 */
package com.feng.learn.basic.old2.learn.pattern.strategy.use;

/**
 * @author feng
 *
 */
public class FirstPriceStrategy implements PriceStrategy {

	/* (non-Javadoc)
	 * @see com.feng.learn.pattern.strategy.use.PriceStrategy#calPrice(double)
	 */
	@Override
	public double calPrice(double price) {
		return price * 0.98;
	}

}
