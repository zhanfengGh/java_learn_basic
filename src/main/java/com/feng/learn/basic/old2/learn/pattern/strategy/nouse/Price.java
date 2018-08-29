/**
 * 
 */
package com.feng.learn.basic.old2.learn.pattern.strategy.nouse;

/**
 * @author feng
 *
 */
public class Price {

	// 根据会员的等级来计算价格折扣
	public double calPrice(double initPrice, Member m) {
		MemberType type = m.getType();
		if (type == MemberType.FIRST) {
			return initPrice * 0.9;
		} else if (type == MemberType.SECOND) {
			return initPrice * 0.8;
		} else if (type == MemberType.THIRD) {
			return initPrice * 0.7;
		} else {
			return initPrice;
		}
	}
}
