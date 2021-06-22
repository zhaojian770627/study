package com.zj.study.algorithm.graph.tarjan;

import java.util.List;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

public class TarjanTest {

	public static void main(String[] args) {
		MutableGraph<Integer> graph = GraphBuilder.directed().incidentEdgeOrder(ElementOrder.stable()).build();
		graph.putEdge(0, 1);
		graph.putEdge(1, 2);
		graph.putEdge(2, 0);

		graph.putEdge(3, 4);
		graph.putEdge(4, 5);
		graph.putEdge(5, 3);

		graph.putEdge(2, 4);

		Tarjan tarjan = new Tarjan(graph);
		List<List<Integer>> result = tarjan.reverse();

		for (List<Integer> ints : result) {
			System.out.println(ints);
		}
	}

}
