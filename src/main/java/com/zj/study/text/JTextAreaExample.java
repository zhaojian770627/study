package com.zj.study.text;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.jute.compiler.JBuffer;

/**
 * A TreeTable example, showing a JTreeTable, operating on the local file
 * system.
 *
 * @version %I% %G%
 *
 * @author Philip Milne
 */

public class JTextAreaExample {
	public static void main(String[] args) {
		new JTextAreaExample();
	}

	public JTextAreaExample() {
		JFrame frame = new JFrame("TreeTable");
//		MyJTextArea textArea = new MyJTextArea();
		JTextArea textArea = new JTextArea();
		JButton topButton = new JButton("TOP");

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		topButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.getUI().viewToModel(textArea, new Point(10, 10));
			}
		});

		textArea.setText("abcd\neeeee");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(topButton), BorderLayout.NORTH);
		frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		frame.pack();
		frame.setSize(600, 800);
		frame.show();
//		textArea.insert("abcd", 0);
	}
}