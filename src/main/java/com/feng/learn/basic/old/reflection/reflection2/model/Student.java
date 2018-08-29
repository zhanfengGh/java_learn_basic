package com.feng.learn.basic.old.reflection.reflection2.model;

public class Student extends Person{

	static {
		System.out.println("Class<Student> init.. start");
		System.out.println("Class<Student> init.. end");
	}
	
	{
		System.out.println("instanceof Student init.. start");
		System.out.println("instanceof Student  init.. end");
	}
	
	private Integer studentNo;
	private Integer grade;
	
	public Student(){};
	
	public Student(Integer studentNo){
		this(studentNo, 1);
	}
	
	public Student(int studentNo, int grade){
		this.studentNo=studentNo;
		this.grade=grade;
	}
	
	public Student(int studentNo, String name){
		super(name);
		this.studentNo=studentNo;
	}
	
	public int getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
}
