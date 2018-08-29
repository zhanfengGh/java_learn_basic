/**
 * 
 */
package com.feng.learn.basic.old2.learn.proxy;

import java.util.List;

/**
 * @author feng
 *
 */
public interface Service {

	int[] serviceA(List<String> list);

	//Object[] serviceA(List<Integer> list);

	String[] serviceB(String[] strs);

}
