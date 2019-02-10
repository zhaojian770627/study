package com.zj.study.java2s.reflection.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class ReflectionTest {
	public static void main(String[] args) {
		String name = "java.util.Date";
		try {
			Class cl = Class.forName(name);
			Class supercl = cl.getSuperclass();
			System.out.print("class " + name);
			if (supercl != null && !supercl.equals(Object.class))
				System.out.println(" extends " + supercl.getName());
			System.out.print("Its constructors:");
			printConstructors(cl);
			System.out.println();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found.");
		}
	}

	public static void printConstructors(Class cl) {
		Constructor[] constructors = cl.getDeclaredConstructors();

		for (int i = 0; i < constructors.length; i++) {
			Constructor c = constructors[i];
			Class[] paramTypes = c.getParameterTypes();
			String name = c.getName();
			System.out.print(Modifier.toString(c.getModifiers()));
			System.out.print(" " + name + "(");
			for (int j = 0; j < paramTypes.length; j++) {
				if (j > 0)
					System.out.print(", ");
				System.out.print(paramTypes[j].getName());
			}
			System.out.println(");");
		}
	}
}