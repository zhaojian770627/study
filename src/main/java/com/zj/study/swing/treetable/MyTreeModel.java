package com.zj.study.swing.treetable;

public class MyTreeModel extends AbstractTreeTableModel {

	public MyTreeModel(Object root) {
		super(root);
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int column) {
		return "ab";
	}

	@Override
	public Object getValueAt(Object node, int column) {
		return "a";
	}

	@Override
	public Object getChild(Object parent, int index) {

		return "b";
	}

	@Override
	public int getChildCount(Object parent) {
		return 2;
	}

}
