package com.zj.study.java2s.reflection.enums;

import java.lang.reflect.Method;

import org.xml.sax.XMLReader;

public class GetMethod {

	public static void main(String[] args) {
		Method[] ms = String.class.getMethods();
		for (int i = 0; i < ms.length; i++) {
			// System.out.println(ms[i].getName());
		}
		Method[] ims = XMLReader.class.getMethods();
		for (int i = 0; i < ims.length; i++) {
			// System.out.println(ims[i].getName());
		}
		Method[] sms = Size.class.getMethods();
		for (int i = 0; i < sms.length; i++) {
			// System.out.println(sms[i].getName());
		}
		String pc = new String();
		try {
			Method setCpu = String.class.getMethod("toString", String.class);
			setCpu.invoke(pc, "Intel 3.0");
			System.out.println(pc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
