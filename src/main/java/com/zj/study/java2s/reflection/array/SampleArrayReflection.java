package com.zj.study.java2s.reflection.array;

import java.awt.Button;
import java.awt.TextField;
import java.lang.reflect.Field;

public class SampleArrayReflection {

	public static void main(String[] args) {
		KeyPad target = new KeyPad();
		printArrayNames(target);
	}

	static void printArrayNames(Object target) {
		Class targetClass = target.getClass();
		Field[] publicFields = targetClass.getFields();
		for (int i = 0; i < publicFields.length; i++) {
			String fieldName = publicFields[i].getName();
			Class typeClass = publicFields[i].getType();
			String fieldType = typeClass.getName();
			if (typeClass.isArray()) {
				System.out.println("Name: " + fieldName + ", Type: " + fieldType);
			}
		}
	}
}

class KeyPad {

	public boolean alive;

	public Button power;

	public Button[] letters;

	public int[] codes;

	public TextField[] rows;

	public boolean[] states;
}