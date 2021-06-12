package com.zj.study.algorithm.heap;

public class MediaFinder {
	MaxHeapPriorityQueue<Integer> maxQueue = new MaxHeapPriorityQueue<Integer>();
	MinHeapPriorityQueue<Integer> minQueue = new MinHeapPriorityQueue<Integer>();

	public MediaFinder add(Integer... nums) {
		for (Integer num : nums) {
			add(num);
		}
		return this;
	}

	public MediaFinder add(Integer num) {
		if (maxQueue.length() == 0)
			maxQueue.add(num, num);
		else {
			if (maxQueue.getTop().getKey().compareTo(num) < 0) {
				minQueue.add(num, num);
				if (minQueue.length() > maxQueue.length()) {
					maxQueue.add(minQueue.popTop());
				}
			} else {
				maxQueue.add(num, num);
				if (maxQueue.length() > minQueue.length() + 1) {
					minQueue.add(maxQueue.popTop());
				}
			}
		}

		return this;
	}

	public int getMedia() {
		return maxQueue.getTop().getValue();
	}

	public static void main(String[] args) {
		MediaFinder mediaFinder = new MediaFinder();
		mediaFinder.add(6, 7, 8, 1, 2);
		System.out.println(mediaFinder.getMedia());
	}

}
