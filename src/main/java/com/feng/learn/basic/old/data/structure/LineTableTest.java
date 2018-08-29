package com.feng.learn.basic.old.data.structure;

public class LineTableTest {
	
	public static void main(String[] args){
		LineTable lt=new LineTable(3);
		lt.add(1);
		lt.add(2);
		lt.add(3);
		lt.add(1,1.1);
		lt.add(10,1.2);
		lt.remove();
		lt.remove(1);
		lt.clear();
		lt.isEmpty();
	}

}
