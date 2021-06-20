package com.zj.study.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.zj.study.algorithm.heap.HeapPriorityQueue.Item;
import com.zj.study.algorithm.heap.MinHeapPriorityQueue;

public class Dijkstra {
	Map<String, Integer> distinctMap = new HashMap<>();
	
	Graph<Integer> graph;
	IVisitor visitor;

	public Graph<Integer> getGraph() {
		return graph;
	}

	public void setGraph(Graph<Integer> graph) {
		this.graph = graph;
	}

	public IVisitor getVisitor() {
		return visitor;
	}

	public void setVisitor(IVisitor visitor) {
		this.visitor = visitor;
	}
	

	public Map<String, Integer> getDistinctMap() {
		return distinctMap;
	}

	public void setDistinctMap(Map<String, Integer> distinctMap) {
		this.distinctMap = distinctMap;
	}

	public Map<String, String> travel(String start, String end) {
		Set<String> visitedSet = new HashSet<>();
		MinHeapPriorityQueue<String, Integer> pq = new MinHeapPriorityQueue<String, Integer>();
		Map<String, String> pathMap = new HashMap<>();

		pq.add(start, 0);
		while (pq.length() > 0) {
			Item top = pq.popTop();
			String curNodeId = top.getKey().toString();

			if (visitedSet.contains(curNodeId))
				continue;

			visitedSet.add(curNodeId);
			visitor.visitor(graph.getVertex(curNodeId));

			if (!StringUtils.isEmpty(end) && curNodeId.equals(end)) {
				return pathMap;
			}

			Integer curDist = (Integer) top.getValue();

			Set<String> neighbors = graph.getNeighbors(curNodeId);
			for (String neighbor : neighbors) {
				// 边距离
				Integer vdist = graph.getCost(curNodeId, neighbor);
				Integer neighDist = distinctMap.get(neighbor);

				Integer diffDist = curDist + vdist;
				if (neighDist == null || diffDist < neighDist) {
					distinctMap.put(neighbor, curDist + vdist);
					pathMap.put(neighbor, curNodeId);
					pq.add(neighbor, diffDist);
				}
			}
		}
		return pathMap;
	}
}
