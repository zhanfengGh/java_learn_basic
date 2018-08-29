/**
 * 
 */
package com.feng.learn.basic.old2.learn.spring.model;

/**
 * @author feng
 *
 */
public class Address {
	private String addr;

	public Address() {

	}

	public Address(String addr) {
		this.addr = addr;
	}

	/**
	 * return the addr
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * the addr to set
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [addr=" + addr + "]";
	}

}
