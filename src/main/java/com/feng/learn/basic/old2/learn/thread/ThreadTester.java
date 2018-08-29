/**
 * 
 */
package com.feng.learn.basic.old2.learn.thread;

import org.junit.*;

import static org.junit.Assert.fail;

/**  
 * @author zhangzhanfeng 
 * @date Dec 21, 2016   
 */
public class ThreadTester {

	public static void main(String[] args) {
		Thread thread = new Thread("feng") {

			@Override
			public void run() {
				System.out.println("x");
				System.out.println("x");
				super.run();
			}

		};
		thread.setName("zzf");
		thread.setDaemon(true);
		thread.start();
		System.out.println("x");
	}

	@Test
	public void testThread1() {
		Thread thread = new Thread() {

			@Override
			public void run() {
				System.out.println("x");
				System.out.println("x");
				super.run();
			}

		};
		thread.setName("zzf");
		thread.setDaemon(true);
		thread.start();
		System.out.println("x");
	}
	
	@Test
	public void test4() {
		for (int i = 0; i < 5; i++) {
			new Thread("testThread") {

				@Override
				public void run() {
					System.out.println("dfsdf");
					System.out.println("dfsdf");
				}
				
			}.start();
		}
	}

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
	public void test() {
		fail("Not yet implemented");
	}

}
