package com.zj.study.algorithm.graph.tarjan;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.google.common.graph.MutableGraph;

import edu.emory.mathcs.backport.java.util.Arrays;

public class Tarjan {
	// 输入信息
	private MutableGraph<Integer> graph;
	// 保存强连联通分量
	private List<List<Integer>> sccsList = new ArrayList<>();
	// DFN(u)为节点u搜索的次序编号(时间戳)
	private int[] DFN;
	// LOW(u)为u或u的子树能够追溯到的最早的栈中节点的次序号
	private int[] LOW;
	// 搜索的次序编号(时间戳)
	private int index;

	// 栈 用于回退节点
	private Stack<Integer> stack;
	// 节点是否在栈中，减少栈中节点的判断
	private boolean[] isInStack;

	// 构造函数
	public Tarjan(MutableGraph<Integer> graph) {
		this.graph = graph;

		int numOfNode = graph.nodes().size();
		DFN = new int[numOfNode];
		LOW = new int[numOfNode];

		// 初始化DFN LOW所有元素都置为-1，DFN【u]=-1代表节点u还没有被访问过
		Arrays.fill(DFN, -1);
		Arrays.fill(LOW, -1);

		this.stack = new Stack<Integer>();
		this.isInStack = new boolean[numOfNode];
	}

	/**
	 * 遍历图节点
	 * 
	 * @return 强联通分量
	 */
	public List<List<Integer>> reverse() {
		for (int i = 0; i < graph.nodes().size(); i++) {
			if (DFN[i] == -1)
				tarjan(i);
		}
		return sccsList;
	}

	/**
	 * Tarjan算法
	 * 
	 * @param curNode
	 */
	private void tarjan(int curNode) {
		// 初始化搜索的次序编号(时间戳)
		DFN[curNode] = LOW[curNode] = ++index;

		// 入栈
		stack.push(curNode);
		isInStack[curNode] = true;

		// 遍历后继节点
		for (Integer succNode : graph.successors(curNode)) {
			if (DFN[succNode] == -1) { // 如果没有被访问过,（-1）代表没有被访问过
				tarjan(succNode); // 递归调用
				LOW[curNode] = Math.min(LOW[curNode], LOW[succNode]); // 更新所能到的上层节点
			} else if (isInStack[succNode]) { // 如果在栈中，并且被访问过

				LOW[curNode] = Math.min(LOW[curNode], DFN[succNode]); // 到栈中最上层的节点
			}
		}

		// 发现是整个强联通分量子树里的最小根
		List<Integer> scc = new ArrayList<Integer>();
		if (LOW[curNode] == DFN[curNode]) {
			// 出栈
			int j = -1;
			while (curNode != j) {
				j = stack.pop();
				isInStack[j] = false;
				scc.add(j);
			}
			sccsList.add(scc);
		}

	}
}
