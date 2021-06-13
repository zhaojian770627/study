package com.zj.study.algorithm.graph;

import java.util.Stack;

public class DFS<T> extends BaseTravel<T> {

	Stack<String> stack = new Stack<String>();

	@Override
	protected String popVertex() {
		return stack.pop();
	}

	@Override
	protected boolean isNotEmpty() {
		return stack.size() > 0;
	}

	@Override
	protected void pushVertex(String nodeId) {
		stack.push(nodeId);
	}

}
