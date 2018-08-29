/**
 * 
 */
package com.feng.learn.basic.old2.learn.pattern.strategy.use;


import com.feng.learn.basic.old2.learn.pattern.strategy.nouse.Member;
import com.feng.learn.basic.old2.learn.pattern.strategy.nouse.MemberType;

import java.util.HashMap;
import java.util.Map;


/**
 * @author feng
 *
 */
public class Price {

	private Map<MemberType, PriceStrategy> context = new HashMap<MemberType, PriceStrategy>();

	{
		context.put(MemberType.FIRST, new FirstPriceStrategy());
		context.put(MemberType.SECOND, new SecondPriceStrategy());
		context.put(MemberType.THIRD, new ThirdPriceStrategy());
	}

	public double calPrice(double price, Member m) {
		return context.get(m.getType()).calPrice(price);
	}

}
