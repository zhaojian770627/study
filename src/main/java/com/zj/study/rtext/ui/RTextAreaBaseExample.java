package com.zj.study.rtext.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.fife.ui.rtextarea.RTextArea;

/**
 * A TreeTable example, showing a JTreeTable, operating on the local file
 * system.
 *
 * @version %I% %G%
 *
 * @author Philip Milne
 */

public class RTextAreaBaseExample {
	public static void main(String[] args) {
		new RTextAreaBaseExample();
	}

	public RTextAreaBaseExample() {
		JFrame frame = new JFrame("TreeTable");
		RTextArea textArea = new RTextArea();

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