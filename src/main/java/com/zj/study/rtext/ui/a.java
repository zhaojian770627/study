package com.zj.study.rtext.ui;

import javax.swing.text.BadLocationException;
import javax.swing.text.GapContent;

public class a {
	public static void main(String[] args) throws BadLocationException {
		GapContent content = new GapContent();
		content.insertString(0, "1234567890");
		content.insertString(5, "10");
		System.out.println(content.getString(0, 1));
	}
}
