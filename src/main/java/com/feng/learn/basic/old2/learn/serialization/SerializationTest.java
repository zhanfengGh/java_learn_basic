/**
 * 
 */
package com.feng.learn.basic.old2.learn.serialization;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author feng
 *
 */
public class SerializationTest {

	private static File file;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		file = new File("/Volumes/RamDisk/Serial.serial");
	}

	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	//@Ignore
//	public void test1() throws IOException {
//		Person p = new Person();
//		p.setAge(26);
//		FileOutputStream fos = new FileOutputStream(file);
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(p);
//		oos.close();
//	}

	/**
	 * 从一个序列化文件中反序列化对象时，会导致类的初始化。 但绝不调用对象的构造器。
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void test11() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object o = ois.readObject();
		ois.close();
		System.out.println(o);
	}

	/**
	 * 对每个对象只序列化一次
	 * 
	 * @throws IOException
//	 */
//	@Test
//	@Ignore
//	public void test2() throws IOException {
//		Person teacher = new Person("teacher", 44);
//		Student stu1 = new Student("stu1", 20);
//		Student stu2 = new Student("stu2", 18);
//		stu1.setTeacher(teacher);
//		stu2.setTeacher(teacher);
//		FileOutputStream fos = new FileOutputStream(file);
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(teacher);
//		oos.writeObject(stu1);
//		oos.writeObject(stu2);
//		oos.writeObject(stu1);
//		oos.close();
//	}
//
//	@Test
//	@Ignore
//	public void test22() throws IOException, ClassNotFoundException {
//		FileInputStream fis = new FileInputStream(file);
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		Object o1 = ois.readObject();
//		Object o2 = ois.readObject();
//		Object o3 = ois.readObject();
//		Object o4 = ois.readObject();
//		ois.close();
//
//		System.out.println(o1);
//		System.out.println(o2);
//		System.out.println(o3);
//		System.out.println(((Student) o2).getTeacher() == ((Student) o3).getTeacher());
//		System.out.println(o4);
//	}
//
//	@Test
//	@Ignore
//	public void test3() throws IOException {
//		Person p = new Person();
//		Class<? extends Person> cp = p.getClass();
//		System.out.println(Person.class == cp);
//		FileOutputStream fos = new FileOutputStream(file);
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(cp);
//		oos.close();
//	}
//
//	@Test
//	@Ignore
//	public void test33() throws IOException, ClassNotFoundException {
//		// Person p = new Person();
//		FileInputStream fis = new FileInputStream(file);
//		ObjectInputStream ois = new ObjectInputStream(fis);
//
//		Object o = ois.readObject();
//		ois.close();
//		Class<? extends Person> cp = (Class<? extends Person>) o;
//		System.out.println(o);
//
//		Person p = new Person();
//	}
//
//	@Test
//	@Ignore
//	public void test4() throws IOException {
//		com.feng.learn.serialization.externalizable.Person p = new com.feng.learn.serialization.externalizable.Person(
//				"feng", 26);
//		FileOutputStream fos = new FileOutputStream(file);
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(p);
//		oos.close();
//	}
//
//	@Test
//	// @Ignore
//	public void test44() throws IOException, ClassNotFoundException {
//		FileInputStream fis = new FileInputStream(file);
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		Object o = ois.readObject();
//		System.out.println(o);
//		ois.close();
//	}

}
