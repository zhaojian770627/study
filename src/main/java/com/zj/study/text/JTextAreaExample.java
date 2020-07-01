package com.zj.study.text;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

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
		MyJTextArea textArea = new MyJTextArea();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		frame.getContentPane().add(new JScrollPane(textArea));
		frame.pack();
		frame.setSize(600, 800);
		frame.show();
	}
}