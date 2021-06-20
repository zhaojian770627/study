package com.zj.study.algorithm.heap;

public class MaxHeapPriorityQueue<K, V> extends HeapPriorityQueue<K, Comparable> {

	@Override
	protected boolean compare(Comparable k1, Comparable k2) {
		return k1.compareTo(k2) > 0;
	}

}
