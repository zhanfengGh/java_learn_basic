package com.feng.learn.basic.old.reflection.reflection2.model;

public class Teacher extends Person {
	
	static {
		System.out.println("Class<Teacher> init.. start");
		System.out.println("Class<Teacher> init.. end");
	}
	
	{
		System.out.println("instanceof Teacher init.. start");
		System.out.println("instanceof Teacher  init.. end");
	}
	
	private int teacherNo;

	public Teacher(){};
	
	public Teacher(int teacherNo){
		this.teacherNo=teacherNo;
	}
	
	public Teacher(int teacherNo, String name){
		super(name);
		this.teacherNo=teacherNo;
	}
	
	public Teacher(int teacherNo, String name, int age){
		super(name, age);
		this.teacherNo=teacherNo;
	}
	
	public int getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(int teacherNo) {
		this.teacherNo = teacherNo;
	}
	
	
	
}
