/**
 * 
 */
package com.feng.learn.basic.old2.learn.singleton;

import java.io.*;

/**
 * @author feng
 *
 */
public class Test {

	public static byte getBytes()[] {
		return new byte[2];
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);

		Person p = Person.getInstance();

		oos.writeObject(p);

		byte[] serializedObj = baos.toByteArray();
		oos.close();
		File f = new File("/Users/feng/java");
		PrintStream pos = new PrintStream(new FileOutputStream(f));

		pos.write(serializedObj);

		System.out.println(serializedObj.length);
		// ByteArrayInputStream bais = new ByteArrayInputStream(serializedObj);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		Object p2 = ois.readObject();
		ois.close();

		System.out.println(p2);

	}

}
