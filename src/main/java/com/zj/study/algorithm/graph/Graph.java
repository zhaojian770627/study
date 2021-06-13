package com.zj.study.algorithm.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph<T> {
	// 是否有向
	boolean directed = false;
	Map<String, Vertex<T>> vertexMap = new HashMap<>();

	public Graph(boolean directed) {
		this.directed = directed;
	}

	public void addVertex(String nodeId) {
		Vertex<T> vertex = new Vertex<T>(nodeId);
		vertexMap.put(nodeId, vertex);
	}

	public Vertex<T> getVertex(String nodeId) {
		return vertexMap.get(nodeId);
	}

	public void addEdge(String from, String to, T cost) {
		if (!vertexMap.containsKey(from))
			addVertex(from);

		if (!vertexMap.containsKey(to))
			addVertex(to);

		vertexMap.get(from).addNeighbor(to, cost);
		if (!directed)
			vertexMap.get(to).addNeighbor(from, cost);
	}

	public Set<String> getNeighbors(String id) {
		Vertex<T> vertex = vertexMap.get(id);
		return vertex.getConnections();
	}
}
