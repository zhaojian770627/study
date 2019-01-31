package com.zj.study.java2s.reflection.classloader;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class Main1 {
	public Main1() {
		Class cls = this.getClass();
		ProtectionDomain pDomain = cls.getProtectionDomain();
		CodeSource cSource = pDomain.getCodeSource();
		URL loc = cSource.getLocation();
		System.out.println(loc);
	}

	public static void main(String[] argv) throws Exception {
		Main1 m = new Main1();
	}
}