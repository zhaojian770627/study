/*
 * www.javagl.de - JTreeTable
 *
 * Copyright (c) 2016 Marco Hutter - http://www.javagl.de
 */
package com.zj.study.swing.treetable2.test;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.zj.study.swing.treetable2.JTreeTable;
import com.zj.study.swing.treetable2.TreeTableModel;

/**
 * A simple example application for the JTreeTable
 */
@SuppressWarnings("javadoc")
public class JTreeTableSimpleExample {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TreeTableModel treeTableModel = ExampleTreeTableModels.createSimple();
		JTreeTable treeTable = new JTreeTable(treeTableModel);
		f.getContentPane().add(new JScrollPane(treeTable));

		f.setSize(400, 400);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
