package com.zj.study.designpattern.pattern.behavioral.visitor;

public abstract class Course {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract void accept(IVisitor visitor);
}
