package com.zj.study.java2s.reflection.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ShowClass {
	public static void main(String[] args) throws ClassNotFoundException {
		Class aClass = Class.forName("javax.swing.JComponent");
		if (aClass.isInterface()) {
			System.out.print(Modifier.toString(aClass.getModifiers()) + " " + typeName(aClass));
		} else if (aClass.getSuperclass() != null) {
			System.out.print(Modifier.toString(aClass.getModifiers()) + " class " + typeName(aClass) + " extends "
					+ typeName(aClass.getSuperclass()));
		} else {
			System.out.print(Modifier.toString(aClass.getModifiers()) + " class " + typeName(aClass));
		}

		Class[] interfaces = aClass.getInterfaces();
		if ((interfaces != null) && (interfaces.length > 0)) {
			if (aClass.isInterface())
				System.out.print(" extends ");
			else
				System.out.print(" implements ");
			for (int i = 0; i < interfaces.length; i++) {
				if (i > 0)
					System.out.print(", ");
				System.out.print(typeName(interfaces[i]));
			}
		}

		System.out.println(" {");

		Constructor[] constructors = aClass.getDeclaredConstructors();
		for (int i = 0; i < constructors.length; i++)
			printMethodOrConstructor(constructors[i]);

		Field[] fields = aClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
			printField(fields[i]);

		Method[] methods = aClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++)
			printMethodOrConstructor(methods[i]);

		System.out.println("}");
	}

	public static String typeName(Class t) {
		String brackets = "";
		while (t.isArray()) {
			brackets += "[]";
			t = t.getComponentType();
		}
		String name = t.getName();
		int pos = name.lastIndexOf('.');
		if (pos != -1)
			name = name.substring(pos + 1);
		return name + brackets;
	}

	public static String modifiers(int m) {
		if (m == 0)
			return "";
		else
			return Modifier.toString(m) + " ";
	}

	public static void printField(Field f) {
		System.out.println("  " + modifiers(f.getModifiers()) + typeName(f.getType()) + " " + f.getName() + ";");
	}

	public static void printMethodOrConstructor(Member member) {
		Class returntype = null, parameters[], exceptions[];
		if (member instanceof Method) {
			Method m = (Method) member;
			returntype = m.getReturnType();
			parameters = m.getParameterTypes();
			exceptions = m.getExceptionTypes();
			System.out.print(
					"  " + modifiers(member.getModifiers()) + typeName(returntype) + " " + member.getName() + "(");
		} else {
			Constructor c = (Constructor) member;
			parameters = c.getParameterTypes();
			exceptions = c.getExceptionTypes();
			System.out.print("  " + modifiers(member.getModifiers()) + typeName(c.getDeclaringClass()) + "(");
		}

		for (int i = 0; i < parameters.length; i++) {
			if (i > 0)
				System.out.print(", ");
			System.out.print(typeName(parameters[i]));
		}
		System.out.print(")");
		if (exceptions.length > 0)
			System.out.print(" throws ");
		for (int i = 0; i < exceptions.length; i++) {
			if (i > 0)
				System.out.print(", ");
			System.out.print(typeName(exceptions[i]));
		}
		System.out.println(";");
	}
}
