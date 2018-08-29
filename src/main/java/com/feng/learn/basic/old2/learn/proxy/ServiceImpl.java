/**
 * 
 */
package com.feng.learn.basic.old2.learn.proxy;

import java.util.List;

/**
 * @author feng
 *
 */
public class ServiceImpl implements Service {

	/* (non-Javadoc)
	 * @see com.feng.learn.proxy.Service#serviceA(java.util.List)
	 */
	@Override
	public int[] serviceA(List<String> list) {
		for (String str : list) {
			System.out.println(str);
		}
		return new int[] { 1, 3, 5 };
	}

	/* (non-Javadoc)
	 * @see com.feng.learn.proxy.Service#serviceB(java.lang.String[])
	 */
	@Override
	public String[] serviceB(String[] strs) {
		// TODO Auto-generated method stub
		return null;
	}

}
