/**
 * 
 */
package com.feng.learn.basic.old2.learn.reflection;

import org.junit.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author feng
 *
 */
public class ATest {

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	@Test
	public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<Person> c = Person.class;
		Method m = c.getMethod("createPerson", String.class, Integer.TYPE);
		Object o = m.invoke(null, "zhangzhanfeng", 18);
		System.out.println(o);
	}

}
