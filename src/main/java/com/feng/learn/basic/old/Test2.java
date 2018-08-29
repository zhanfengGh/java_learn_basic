package com.feng.learn.basic.old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {
	public static void main(String[] args) {
		Integer[] argsType=new Integer[]{2,5,1,1,3};
		List<int[]> argsSuperTypes=new ArrayList<int[]>();
		for (int argType:argsType){
			int[] temp=new int[argType];
			for (int i=0;i<argType;i++){
				temp[i]=i;
			}
			argsSuperTypes.add(temp);
		}
		int[] point=new int[argsType.length];
		while (point[point.length-1]<argsSuperTypes.get(argsType.length-1).length){
			for (int i=0;i<argsType.length;i++){
				argsType[i]=argsSuperTypes.get(i)[point[i]];
			} 
			System.out.println(Arrays.asList(argsType));
			// 驱动指针的推动
			point[0]++;
			
			for (int i=0;i<point.length-1;i++){
				if (point[i]>=argsSuperTypes.get(i).length){
					point[i]=0;
					point[i+1]++;
				}
			}
		}
		
	}

}
