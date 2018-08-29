/**
 * 
 */
package com.feng.learn.basic.old2.learn.pattern;

import com.feng.learn.basic.old2.learn.pattern.strategy.observer.FrontOfficer;
import com.feng.learn.basic.old2.learn.pattern.strategy.observer.Zhang;
import com.feng.learn.basic.old2.learn.pattern.strategy.observer.Zhu;
import org.junit.*;

/**
 * @author feng
 *
 */
public class PatterTest {

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
	public void testObserver() {
		FrontOfficer o = new FrontOfficer();
		o.addListener(new Zhang());
		o.addListener(new Zhu());
		o.bossAtDoor();
	}

}
