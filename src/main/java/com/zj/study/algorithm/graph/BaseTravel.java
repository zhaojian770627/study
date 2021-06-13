package com.zj.study.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class BaseTravel<T> implements ITravel {
	Graph<T> graph;
	IVisitor visitor;

	public Graph<T> getGraph() {
		return graph;
	}

	public void setGraph(Graph<T> graph) {
		this.graph = graph;
	}

	public IVisitor getVisitor() {
		return visitor;
	}

	public void setVisitor(IVisitor visitor) {
		this.visitor = visitor;
	}

	@Override
	public Map<String, String> travel(String start, String end) {
		// 是否已经访问过列表
		Set<String> visitedSet = new HashSet<>();
		Map<String, String> pathMap = new HashMap<>();

		pushVertex(start);
		while (isNotEmpty()) {
			String curNodeId = popVertex();

			if (visitedSet.contains(curNodeId))
				continue;

			visitedSet.add(curNodeId);
			visitor.visitor(graph.getVertex(curNodeId));

			if (curNodeId.equals(end)) {
				return pathMap;
			}

			Set<String> neighbors = graph.getNeighbors(curNodeId);
			for (String neighbor : neighbors) {
				pathMap.put(neighbor, curNodeId);
				pushVertex(neighbor);
			}
		}

		return pathMap;

	}

	protected abstract String popVertex();

	protected abstract boolean isNotEmpty();

	protected abstract void pushVertex(String start);
}
