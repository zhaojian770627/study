package com.zj.study.algorithm.graph;

import java.util.Map;

public class TravelTest {

	public static void main(String[] args) {
		Graph<Integer> graph = createGraph();
//		BaseTravel<Integer> travel = new DFS<>();
		BaseTravel<Integer> travel = new BFS<>();
		travel.setGraph(graph);
		travel.setVisitor(new PrintVisitor());
		Map<String, String> travelMap = travel.travel("a", "c");
		travelMap.forEach((k, v) -> {
			System.out.println(v + "========>" + k);
		});
	}

	private static Graph<Integer> createGraph() {
		Graph<Integer> graph = new Graph<Integer>(true);
		graph.addEdge("a", "b", 10);
//		graph.addEdge("a", "c", 11);
		graph.addEdge("b", "c", 20);
		return graph;
	}

}
