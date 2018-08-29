package com.feng.learn.basic.old;

public class Test {

	public static void main(String[] args) {
		int[] test=new int[10];
		
		try {
			testException();
			System.out.println("...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("After catch..");

		char c = 'c';
		char a = 97;
		int d = c + a;
		System.out.println(a);
		System.out.println();
		int[] arr=new int[100];
		//arr = { 1, 2, 3 };
		arr = new int[] { 1, 2, 3 };
		System.out.println(arr);
		Number[] arr3;
		Long[] arr6;
		Double[] arr4;
		Integer[] arr2 = { 1, 2, 3 };
		arr3 = arr2;
		//arr6 = ar2;
		//arr4 = arr2;
		//long[] arr5; // arr5=arr; //结论 Integer[] 是Numbe[]的子类 //

		new Student();

		System.out.println("11111");
		new Student("feng", 26);
		System.out.println("2222");
		Person s = new Student("feng", 26, 997);
		System.out.println(s.getName());

	}

	public static void testException() {
		throw new RuntimeException("HelloWorld");
	}
}
