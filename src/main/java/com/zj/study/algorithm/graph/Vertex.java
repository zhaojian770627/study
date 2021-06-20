package com.zj.study.algorithm.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex<T> {
	String id;
	Map<String, T> adjacent = new HashMap<>();

	public Vertex(String id) {
		this.id = id;
	}

	public void addNeighbor(String id, T cost) {
		adjacent.put(id, cost);
	}

	public Set<String> getConnections() {
		return adjacent.keySet();
	}

	public T getCost(String neighbor) {
		return adjacent.get(neighbor);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
