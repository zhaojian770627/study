package com.zj.study.algorithm.heap;

import java.util.ArrayList;
import java.util.List;

public class HeapPriorityQueue<K extends Comparable, V> {
	public class Item {
		K key;
		V value;

		public Item(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Item [key=" + key + ", value=" + value + "]";
		}
	}

	List<Item> data = new ArrayList<>();

	public void add(K k, V v) {
		add(new Item(k, v));
	}

	private void add(Item item) {
		data.add(item);
		upHead(data.size() - 1);
	}

	public HeapPriorityQueue<K, V>.Item getMin() {
		if (data.size() == 0)
			return null;

		Item minItem = data.get(0);

		data.set(0, data.get(data.size() - 1));
		data.remove(data.size() - 1);

		if (data.size() > 1) {
			downHead(0);
		}

		return minItem;
	}

	private void downHead(int pos) {

	}

	private void upHead(int pos) {
		int parent = parent(pos);
		while (pos > 0 && data.get(pos).getKey().compareTo(data.get(parent).getKey()) < 0) {
			swap(pos, parent);
			pos = parent;
			parent = parent(pos);
		}
//		            self._swap(j, parent) 
//		            self._upheap(parent) 
	}

	private void swap(int pos, int parent) {
		Item tmp = data.get(pos);
		data.set(pos, data.get(parent));
		data.set(parent, tmp);
	}

	private int parent(int pos) {
		return (pos - 1) / 2;
	}

	private int left(int pos) {
		return 2 * pos + 1;
	}

	private int right(int pos) {
		return 2 * pos + 2;
	}

	private boolean hasLeft(int pos) {
		return left(pos) < data.size();
	}

	private boolean hasRight(int pos) {
		return right(pos) < data.size();
	}

	public void print() {
		data.forEach(item -> {
			System.out.println(item);
		});
	}
}
