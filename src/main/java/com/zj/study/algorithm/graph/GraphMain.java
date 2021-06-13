package com.zj.study.algorithm.graph;

public class GraphMain {

	public static void main(String[] args) {
		Graph<Integer> graph = createGraph();
	}

	private static Graph<Integer> createGraph() {
		Graph<Integer> graph = new Graph<Integer>(true);
		graph.addEdge("a", "b", 10);
		graph.addEdge("a", "c", 11);
		graph.addEdge("b", "c", 20);
		return graph;
	}

}
