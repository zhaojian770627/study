package com.zj.study.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS<T> extends BaseTravel<T> {

	Queue<String> queue = new LinkedList<String>();

	@Override
	protected String popVertex() {
		return queue.poll();
	}

	@Override
	protected boolean isNotEmpty() {
		return queue.size() > 0;
	}

	@Override
	protected void pushVertex(String nodeId) {
		queue.offer(nodeId);
	}

}
