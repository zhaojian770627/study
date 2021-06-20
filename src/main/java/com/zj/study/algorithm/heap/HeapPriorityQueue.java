package com.zj.study.algorithm.heap;

import java.util.ArrayList;
import java.util.List;

public abstract class HeapPriorityQueue<K, V extends Comparable> {
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

	public void add(Item item) {
		data.add(item);
		upHead(data.size() - 1);
	}

	public HeapPriorityQueue<K, V>.Item popTop() {
		if (data.size() == 0)
			return null;

		Item minItem = data.get(0);
		data.set(0, data.get(data.size() - 1));
		data.remove(data.size() - 1);
		downHead(0);
		return minItem;
	}

	public HeapPriorityQueue<K, V>.Item getTop() {
		if (data.size() == 0)
			return null;

		return data.get(0);
	}

	private void downHead(int pos) {
		while (hasLeft(pos)) {
			int leftPos = left(pos);
			int smallPos = leftPos;
			if (hasRight(pos)) {
				int rightPos = right(pos);
				if (compare(data.get(rightPos).getValue(), data.get(leftPos).getValue())) {
					smallPos = rightPos;
				}
			}
			if (compare(data.get(smallPos).getValue(), data.get(pos).getValue())) {
				swap(smallPos, pos);
				pos = smallPos;
			} else
				return;
		}
	}

	protected abstract boolean compare(V k1, V k2);

	private void upHead(int pos) {
		int parent = parent(pos);
		while (pos > 0 && compare(data.get(pos).getValue(), data.get(parent).getValue())) {
			swap(pos, parent);
			pos = parent;
			parent = parent(pos);
		}
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

	public int length() {
		return data.size();
	}
}
