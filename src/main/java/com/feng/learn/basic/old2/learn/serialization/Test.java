/**
 * 
 */
package com.feng.learn.basic.old2.learn.serialization;

import java.io.*;

/**
 * @author feng
 *
 */
public class Test {
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		Person11 p = new Person11();
		p.setAge(26);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);

		oos.writeObject(p);
		oos.flush();

		byte[] bs = baos.toByteArray();
		oos.close();

		ByteArrayInputStream bais = new ByteArrayInputStream(bs);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object o = ois.readObject();
		System.out.println(o.getClass());
		Person11 pi = null;
		if (o.getClass() == Person11.class) {
			pi = (Person11) o;
		}
	}
}
