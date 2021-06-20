package com.zj.study.algorithm.graph;

import java.util.Map;

public class DijkstraTest {

	public static void main(String[] args) {
		Graph<Integer> graph = createGraph();
		Dijkstra travel = new Dijkstra();
		travel.setGraph(graph);
		travel.setVisitor(new PrintVisitor());
		Map<String, String> travelMap = travel.travel("a", "e");
		travelMap.forEach((k, v) -> {
			System.out.println(v + "========>" + k);
		});

		System.out.println("距离");

		travel.getDistinctMap().forEach((k, v) -> {
			System.out.println(k + "==>" + v);
		});
	}

	private static Graph<Integer> createGraph() {
		Graph<Integer> graph = new Graph<Integer>(true);
		graph.addEdge("a", "b", 4);
		graph.addEdge("a", "c", 1);
		graph.addEdge("c", "b", 2);
		graph.addEdge("b", "e", 4);
		graph.addEdge("c", "d", 4);
		graph.addEdge("d", "e", 4);
		return graph;
	}

}
