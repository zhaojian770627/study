package com.zj.study.algorithm.heap;

import com.zj.study.algorithm.heap.HeapPriorityQueue.Item;

public class HeapMain {

	public static void main(String[] args) {
		HeapPriorityQueue<Integer, Integer> queue = new HeapPriorityQueue<Integer, Integer>();
		queue.add(6, 6);
		queue.add(4, 4);
		queue.add(8, 8);
		queue.add(1, 1);
		queue.add(12, 12);
		queue.add(30, 30);
		queue.add(3, 3);
		queue.print();

		Item min = queue.getMin();
		while (min != null) {
			System.out.println(min.getKey());
			min = queue.getMin();
		}
	}

}
