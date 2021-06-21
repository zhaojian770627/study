package com.zj.study.algorithm.graph.tarjan;

import java.util.List;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

public class TarjanTest {

	public static void main(String[] args) {
		MutableGraph<Integer> graph = GraphBuilder.directed().incidentEdgeOrder(ElementOrder.stable()).build();
		graph.putEdge(0, 2);
		graph.putEdge(0, 1);
		graph.putEdge(1, 3);
		graph.putEdge(2, 4);
		graph.putEdge(2, 3);
		graph.putEdge(3, 0);
		graph.putEdge(3, 5);
		graph.putEdge(4, 5);

		Tarjan tarjan = new Tarjan(graph);
		List<List<Integer>> result = tarjan.reverse();

		// 校验
		System.out.println(result.size());
		System.out.println("[5]====>" + result.get(0).toString());
		System.out.println("[4]====>" + result.get(1).toString());
		System.out.println("[1,3,2,0]====>" + result.get(2).toString());
	}

}
