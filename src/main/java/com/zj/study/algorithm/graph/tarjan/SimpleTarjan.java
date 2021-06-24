package com.zj.study.algorithm.graph.tarjan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.common.graph.MutableGraph;

public class SimpleTarjan {
	// 输入信息
	private MutableGraph<String> graph;

	private Map<String, Integer> DFN;
	// LOW(u)为u或u的子树能够追溯到的最早的栈中节点的次序号
	private Map<String, Integer> LOW;

	// 搜索的次序编号(时间戳)
	private int index;

	// 栈 用于回退节点
	private Stack<String> stack;
	// 节点是否在栈中，减少栈中节点的判断
	private Set<String> isInStack;

	private List<List<String>> sccsList = new ArrayList<>();

	public SimpleTarjan(MutableGraph<String> graph) {
		this.graph = graph;

		DFN = new HashMap<>();
		LOW = new HashMap<>();

		graph.nodes().forEach(v -> {
			DFN.put(v, -1);
			LOW.put(v, -1);
		});

		this.stack = new Stack<String>();
		this.isInStack = new HashSet<>();
	}

	public List<List<String>> reverse() {
		sccsList.clear();
		for (String node : graph.nodes()) {
			if (DFN.get(node) == -1) {
				tarjan(node);
			}
		}
		return sccsList;
	}

	private void tarjan(String curNode) {
		stack.push(curNode);
		index++;
		DFN.put(curNode, index);
		LOW.put(curNode, index);
		isInStack.add(curNode);

		for (String succNode : graph.successors(curNode)) {
			if (DFN.get(succNode) == -1) {
				tarjan(succNode);
				LOW.put(curNode, Math.min(LOW.get(curNode), LOW.get(succNode)));
			} else if (isInStack.contains(succNode)) {
				LOW.put(curNode, Math.min(LOW.get(curNode), LOW.get(succNode)));
			}
		}

		List<String> cycleNodes = new ArrayList<>();
		if (DFN.get(curNode).equals(LOW.get(curNode))) {
			String popNode = "";
			while (!curNode.equals(popNode)) {
				popNode = stack.pop();
				isInStack.remove(popNode);
				cycleNodes.add(popNode);
			}
			sccsList.add(cycleNodes);
		}
	}
}
