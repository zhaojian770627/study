package com.zj.study.zookeeper;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private int age;
	private int grade;
	
	public Student(String userName, int age, int grade) {
		super();
		this.userName = userName;
		this.age = age;
		this.grade = grade;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Student [userName=" + userName + ", age=" + age + ", grade=" + grade + "]";
	}
	
	
}
