package com.zj.study.algorithm.heap;

public class MinHeapPriorityQueue<V> extends HeapPriorityQueue<Comparable, V> {

	@Override
	protected boolean compare(Comparable k1, Comparable k2) {
		return k1.compareTo(k2) < 0;
	}

}
