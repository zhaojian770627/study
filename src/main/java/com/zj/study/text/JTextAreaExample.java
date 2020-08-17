package com.zj.study.text;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JTextAreaExample {
	public static void main(String[] args) {
		new JTextAreaExample();
	}

	public JTextAreaExample() {
		JFrame frame = new JFrame("TreeTable");
//		MyJTextArea textArea = new MyJTextArea();
		JTextArea textArea = new JTextArea();
		JPanel myArea = new JPanel();
		myArea.setPreferredSize(new Dimension(500, 500));
		JButton topButton = new JButton("TOP");
		JButton leftButton = new JButton("LEFT");

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		topButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = textArea.getUI().viewToModel(textArea, new Point(10, 10));
				JOptionPane.showMessageDialog(null, String.valueOf(index));
			}
		});
		leftButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		myArea.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				JOptionPane.showMessageDialog(null, "X:" + e.getX() + " Y:" + e.getY());
			}

		});

		textArea.setText("abcdeeeeeabcdeeeeeabcdeeeeeabcdeeeeeabcdeeeee");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(topButton, BorderLayout.NORTH);
		frame.getContentPane().add(leftButton, BorderLayout.WEST);
		frame.getContentPane().add(new JScrollPane(myArea), BorderLayout.CENTER);
		frame.pack();
		frame.setSize(200, 200);
		frame.show();
//		textArea.insert("abcd", 0);
	}
}