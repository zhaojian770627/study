package com.zj.study.swing.treetable;

public class MyTreeModel extends AbstractTreeTableModel {

	private Node data;

	public MyTreeModel(Node root) {
		super(root);
		this.data = root;
	}

	@Override
	public int getColumnCount() {
		return data.getValues().length;
	}

	@Override
	public String getColumnName(int column) {
		return "value" + column;
	}

	@Override
	public Object getValueAt(Object node, int column) {
		Node n = (Node) node;
		return "data" + n.getValues()[column];
	}

	@Override
	public Object getChild(Object parent, int index) {
		Node p = (Node) parent;
		return p.childs.get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		Node p = (Node) parent;
		return p.getChilds().size();
	}

}
