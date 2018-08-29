package com.feng.learn.basic.old.data.structure;

public interface LineTableI {
	
	//public void init();
	
	public boolean add(Object o);
	
	public boolean add(int index, Object o);
	
	public Object remove();
	
	public Object remove(int index);
	
	public int size();
	
	public Object get(int index);
	
	public int getIndex(Object o);
	

	public boolean isEmpty();
	
	public void clear();


}
