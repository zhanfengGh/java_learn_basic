/**
 * 
 */
package com.feng.learn.basic.old2.learn;

/**
 * @author feng
 *
 */
class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}

class Human {
	private volatile int a = 10;

	public static void main(String[] args) throws Exception {
		try {
			try {
				throw new Sneeze();
			} catch (Annoyance a) {
				System.out.println("Caught Annoyance");
				throw a;
			}
		} catch (Sneeze s) {
			System.out.println("Caught Sneeze");
			return;
		} finally {
			System.out.println("Hello World!");
		}
	}
}
