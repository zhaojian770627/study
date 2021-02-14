package com.zj.study.designpattern.pattern.behavioral.visitor;

public class CodingCourse extends Course {
	private double price;

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

}
