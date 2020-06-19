package com.zj.study.compile.netcourse.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleASTNode implements ASTNode {
	// 父节点
	SimpleASTNode parent = null;

	// 子节点
	List<ASTNode> children = new ArrayList<>();
	List<ASTNode> readonlyChildren = Collections.unmodifiableList(children);
	// 类型
	ASTNodeType nodeType = null;
	String text = null;

	public SimpleASTNode(ASTNodeType nodeType, String tokenText) {
		this.nodeType = nodeType;
		this.text = tokenText;
	}

	@Override
	public ASTNode getParent() {
		return parent;
	}

	@Override
	public List<ASTNode> getChildren() {
		return readonlyChildren;
	}

	@Override
	public ASTNodeType getType() {
		return nodeType;
	}

	@Override
	public String getText() {
		return text;
	}

	public void addChild(SimpleASTNode child) {
		children.add(child);
		child.parent = this;
	}

	/**
	 * 打印输出AST的树状结构
	 * 
	 * @param node
	 * @param indent 缩进字符，由tab组成，每一级多一个tab
	 */
	public static void dumpAST(ASTNode node, String indent) {
		System.out.println(indent + node.getType() + " " + node.getText());
		for (ASTNode child : node.getChildren()) {
			dumpAST(child, indent + "\t");
		}
	}
}
