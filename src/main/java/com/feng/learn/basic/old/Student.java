package com.feng.learn.basic.old;

public class Student extends Person {
	
	static{
		System.out.println("Class Student init...");
	}
	
	private int classNo;
	
	public Student(){
		//super("feng",18);
		System.out.println("Student()...");
	}
	
	public Student(String name,int age){
		super(name,age);
		System.out.println("Student(String,int)...");
		this.classNo=00001;
	}
	
	public Student(String name,int age,int classNo){
		this(name,age);
		System.out.println("Student(String,int,int)...");
		this.classNo=classNo;
	}
	
	/*
	@Override
	public String getName(){
		return "student.getName()..";
	}
	*/
	
	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}
	
	public static void main(String[] args){
		
		System.out.println("main execute....");
		
		Student s =new Student("feng",26,9888);
		System.out.println(s.getClassNo());
	}

}
