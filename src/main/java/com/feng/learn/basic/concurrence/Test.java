package com.feng.learn.basic.concurrence;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String args[]){
		/*
		 for (int i=1;i<1000000;i++){
			int count=0;
			for (int j=1;j<=i;j++){
				if (i%j==0)
					count++;
			}
			if (count==2){
				System.out.println(i);
			}
		}
		*/
		/*for(int count=0;count<10;count++){
			System.out.println(count);
			count*=0.1;
		}*/
		List<Integer> list=new ArrayList<Integer>();
		list.add(5);
		System.out.println(list.size());
		System.out.println(list.get(0));
		System.out.println(list.get(list.size()));
	}

}
