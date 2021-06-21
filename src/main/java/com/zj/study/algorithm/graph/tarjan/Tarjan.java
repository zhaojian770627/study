package com.zj.study.algorithm.graph.tarjan;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.google.common.graph.MutableGraph;

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
	public Tarjan() {
	}

}
