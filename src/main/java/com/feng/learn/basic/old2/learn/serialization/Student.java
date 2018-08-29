/**
 * 
 */
package com.feng.learn.basic.old2.learn.serialization;

/**
 * @author feng
 *
 */
public class Student extends Person11 {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2122601887288301568L;
	private Person11 teacher;

	public Student(String name, int age) {
		super(name, age);
	}

	/**
	 * return the teacher
	 * 
	 * @return the teacher
	 */
	public Person11 getTeacher() {
		return teacher;
	}

	/**
	 * the teacher to set
	 * 
	 * @param teacher
	 *            the teacher to set
	 */
	public void setTeacher(Person11 teacher) {
		this.teacher = teacher;
	}

}
