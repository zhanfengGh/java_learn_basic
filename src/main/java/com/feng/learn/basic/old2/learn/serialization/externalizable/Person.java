/**
 * 
 */
package com.feng.learn.basic.old2.learn.serialization.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 
 * serialVersionUID 对实现Externalizable接口的类和实现Serializable接口的类的作用是一样的。
 * 实现Externalizable接口的类的反序列化时会调用类的默认构造器
 * 
 * @author feng
 *
 */
public class Person implements Externalizable {

	private static final long serialVersionUID = -1L;

	private String name;
	private int age;
	static {
		System.out.println("Person implements Externalizable static block()");
	}

	{
		System.out.println("Person implements Externalizable block()");
	}

	public Person() {
		System.out.println("Person implements Externalizable constructor()");
	}

	public Person(String name, int age) {
		System.out.println("Person implements Externalizable constructor(String, int)");
		this.name = name;
		this.age = age;
	}

	/**
	 * return the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * the name to set
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * return the age
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * the age to set
	 * 
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(name);
		out.writeInt(age);
		out.writeObject("HelloWorld");
		out.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name = (String) in.readObject();
		this.age = in.readInt();
		System.out.println(in.readObject());
	}

}
