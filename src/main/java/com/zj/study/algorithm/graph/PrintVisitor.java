package com.zj.study.algorithm.graph;

public class PrintVisitor implements IVisitor {

	@Override
	public void visitor(Vertex<?> vertex) {
		System.out.println(vertex.getId());
	}

}
