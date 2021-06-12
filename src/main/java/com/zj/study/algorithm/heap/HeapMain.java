package com.zj.study.algorithm.heap;

import com.zj.study.algorithm.heap.HeapPriorityQueue.Item;

public class HeapMain {

	public static void main(String[] args) {
		MinHeapPriorityQueue<Integer> minQueue = new MinHeapPriorityQueue<Integer>();
		MaxHeapPriorityQueue<Integer> maxQueue = new MaxHeapPriorityQueue<Integer>();
		minQueue.add(6, 6);
		minQueue.add(4, 4);
		minQueue.add(8, 8);
		minQueue.add(1, 1);
		minQueue.add(12, 12);
		minQueue.add(30, 30);
		minQueue.add(3, 3);
		minQueue.print();
		
		maxQueue.add(6, 6);
		maxQueue.add(4, 4);
		maxQueue.add(8, 8);
		maxQueue.add(1, 1);
		maxQueue.add(12, 12);
		maxQueue.add(30, 30);
		maxQueue.add(3, 3);
		maxQueue.print();

		Item min = minQueue.popTop();
		while (min != null) {
			System.out.println(min.getKey());
			min = minQueue.popTop();
		}
		
		System.out.println("--------------------------------------------------------");
		Item max = maxQueue.popTop();
		while (max != null) {
			System.out.println(max.getKey());
			max = maxQueue.popTop();
		}
		
	}

}
