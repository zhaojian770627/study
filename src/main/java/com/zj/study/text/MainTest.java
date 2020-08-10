package com.zj.study.text;

public class MainTest {
	private static String mappingPath = "mapper";

	public static void main(String[] args) {
		MainTest main = new MainTest();
		main.eval();
	}

	void eval() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String s = "file [D:/zj/git/mid/dev/fibasecom/fibasecom-core/target/classes/mapper/fitplMapper.xml]";
				s = s.replaceAll("\\\\", "/");
				s = s.substring("file [".length(), s.lastIndexOf(mappingPath) + mappingPath.length());
				System.out.println(s);
			}
		}).start();

	}

}
