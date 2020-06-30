package com.zj.study.swing.treetable;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TestMain {

	public static void main(String[] args) {
		JFrame jf = new JFrame("测试窗口");
		jf.setSize(200, 200);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Node root = new Node("root");
		
		root.addNode(new Node("child1"));
//		root.addNode(new Node("child2"));

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		MyTreeModel model = new MyTreeModel(root);
		JTreeTable treeTable = new JTreeTable(model);
		panel.add(treeTable, BorderLayout.CENTER);

		jf.setContentPane(panel);
		jf.setVisible(true);
	}

}
