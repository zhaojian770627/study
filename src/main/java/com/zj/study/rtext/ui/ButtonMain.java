package com.zj.study.rtext.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class ButtonMain {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); 
		JFrame jf = new JFrame("测试窗口");
		jf.setSize(200, 200);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		// 创建一个按钮
		final JButton btn = new JButton("测试按钮");

		// 添加按钮的点击事件监听器
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取到的事件源就是按钮本身
				// JButton btn = (JButton) e.getSource();
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("按钮被点击");
			}
		});

		panel.add(btn);
		String lafName = UIManager.getLookAndFeel().getName();
		System.out.println(lafName);
		jf.setContentPane(panel);
		jf.setVisible(true);
	}
}