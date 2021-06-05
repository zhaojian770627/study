package com.zj.study.algorithm.heap;

public class HeapMain {

	public static void main(String[] args) {
		HeapPriorityQueue<Integer, Integer> queue = new HeapPriorityQueue<Integer, Integer>();
		queue.add(6, 6);
		queue.add(4, 4);
		queue.add(8, 8);
		queue.add(1, 1);
		queue.print();
	}

}
