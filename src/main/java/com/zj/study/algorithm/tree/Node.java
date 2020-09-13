package com.zj.study.algorithm.tree;

/**
 * 
 * 二叉树的节点
 * 
 * @author zhaojian
 *
 * @param <T>
 */
public class Node<T> {
	T value;
	Node<T> left;
	Node<T> right;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

}
