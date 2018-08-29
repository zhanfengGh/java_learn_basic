/**
 * 
 */
package com.feng.learn.basic.old2.learn.reflection;

/**
 * @author feng
 *
 */
public class Teacher extends Person {
	private String mainObj; // 主教课程

	static {
		System.out.println("Teacher.class init..");
	}

	{
		System.out.println("Teacher instance init..");
	}

	/**
	 * return the mainObj
	 * 
	 * @return the mainObj
	 */
	public String getMainObj() {
		return mainObj;
	}

	/**
	 * the mainObj to set
	 * 
	 * @param mainObj
	 *            the mainObj to set
	 */
	public void setMainObj(String mainObj) {
		this.mainObj = mainObj;
	}

}
