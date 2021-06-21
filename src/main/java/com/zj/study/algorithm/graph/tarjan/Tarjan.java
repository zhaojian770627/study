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

}
