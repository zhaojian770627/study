package com.zj.study.designpattern.pattern.behavioral.mediator;

public class User {
	private String name;

	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void sendMessage(String message) {
		StudyGroup.showMessage(this, message);
	}
}
