package com.zj.study.rtext.ui;

import javax.swing.SwingUtilities;

import org.fife.rtext.AWTExceptionHandler;

public class RText {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		AWTExceptionHandler.register();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

			}
		});
	}

}
