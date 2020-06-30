package com.zj.study.swing.treetable;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class TestMain {

	public static void main(String[] args) {
		JFrame jf = new JFrame("测试窗口");
		jf.setSize(200, 200);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JTree tree = new JTree();
		MyTreeModel model = new MyTreeModel("aaa");
		JTreeTable treeTable = new JTreeTable(model);
		panel.add(treeTable, BorderLayout.CENTER);

		jf.setContentPane(panel);
		jf.setVisible(true);
	}

}
