package com.zj.study.algorithm.recursion;

import java.util.HashSet;
import java.util.Set;

public class PermLetterMain {

	static Set<String> keys = new HashSet<>();
	static Set<String> results = new HashSet<>();

	public static void main(String[] args) {
		String word = "medium-one";
		String rule = "io";
		PermLetterMain main = new PermLetterMain();
		main.permLetter(word, rule);

		for (String result : results) {
			System.out.println(result);
		}
	}

	private void permLetter(String word, String rule) {
		rule = rule.toLowerCase();
		for (char c : rule.toCharArray()) {
			keys.add(String.valueOf(c));
		}
		permHelper(word, rule, 0, "");
	}

	private void permHelper(String word, String rule, int index, String prefix) {
		int length = word.length();

		for (int i = index; i < length; i++) {
			String c = String.valueOf(word.charAt(i));
			if (keys.contains(c)) {
				permHelper(word, rule, i + 1, prefix + c);
				c = c.toUpperCase();
				permHelper(word, rule, i + 1, prefix + c);
			} else
				prefix += c;
		}

		if (prefix.length() == word.length())
			results.add(prefix);
	}

}
