/**
 * 
 */
package com.feng.learn.basic.old2.learn.proxy;

import org.junit.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import static org.junit.Assert.fail;

/**
 * @author feng
 *
 */
public class ProxyTest {

	private static Service service;
	private static InvocationHandler h;

	@Test
	public void test1() {
		Service service = (Service) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class<?>[] { Service.class }, h);
		int[] datas = service.serviceA(Arrays.asList("feng", "zhang"));
		for (int i : datas) {
			System.out.println(i);
		}
	}

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new ServiceImpl();
		h = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// do something before

				// 这里甚至不用调用method.invoke()方法，你爱怎么操作就怎么操作
				Object result = method.invoke(service, args);

				// do something after

				return result;
			}

		};
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

	//@Test
	public void test() {
		fail("Not yet implemented");
	}

}
