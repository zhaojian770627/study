package com.zj.study.algorithm.recursion;

public class PrintRule {

	public static void main(String[] args) {
		PrintRule rule = new PrintRule();
//		System.out.println(rule.one(4));
//		System.out.println(rule.two(4));
//		System.out.println(rule.three(4));
		rule.draw_rule(2, 2);
	}

	private String three(int n) {
		String temp = "1";
		for (int i = 2; i <= n; i++) {
			temp = temp + " " + i + " " + temp;
		}
		return temp;
	}

	String one(int n) {
		if (n == 1)
			return "1";

		return one(n - 1) + " " + n + " " + one(n - 1);
	}

	String two(int n) {
		if (n == 1)
			return "1";

		String temp = one(n - 1);

		return temp + " " + n + " " + temp;
	}

	void draw_line(int tick_length, String tick_label) {
		String tmp = "-";
		String line = "";
		for (int i = 0; i < tick_length; i++) {
			line = line + tmp;
		}
		if (tick_label != null)
			line += " " + tick_label;
		System.out.println(line);
	}

	void draw_interval(int center_length) {
		if (center_length > 0) {
			draw_interval(center_length - 1);
			draw_line(center_length, "");
			draw_interval(center_length - 1);
		}
	}

	void draw_rule(int num_inches, int major_length) {
		draw_line(major_length, "0");

		for (int j = 1; j < 1 + num_inches; j++) {
			draw_interval(major_length - 1);
			draw_line(major_length, String.valueOf(j));
		}
	}
}
