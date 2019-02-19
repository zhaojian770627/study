package com.zj.study.java2s.reflection.enums;

import org.xml.sax.XMLReader;

public class IsEnum {

	public static void main(String[] args) {
		System.out.println(Size.class.isEnum());
		System.out.println(String.class.isEnum());
		System.out.println(String.class.isInstance("Test"));
		System.out.println(XMLReader.class.isInterface());
	}
}
