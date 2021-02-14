package com.zj.study.designpattern.pattern.behavioral.visitor;

public interface IVisitor {
	void visit(FreeCourse freeCourse);

	void visit(CodingCourse codingCourse);
}
