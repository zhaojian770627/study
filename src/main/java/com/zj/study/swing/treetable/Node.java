package com.zj.study.swing.treetable;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String name;
	String[] values = { "1", "2", "3" };

	List<Node> childs = new ArrayList<>();

	public Node(String name) {
		this.name = name;
	}

	public void addNode(Node c) {
		childs.add(c);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public List<Node> getChilds() {
		return childs;
	}
}
