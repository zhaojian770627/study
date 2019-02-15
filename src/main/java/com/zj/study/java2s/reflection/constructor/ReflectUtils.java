package com.zj.study.java2s.reflection.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.util.List;

/**
 * Reflection Utility methods
 * 
 * @author Ayman Al-Sairafi
 */
public class ReflectUtils {
	/**
	 * Adds all Constructor (from Class.getConstructorCalls) to the list
	 * 
	 * @param aClass
	 * @param list
	 * @return number of constructors added
	 */
	public static int addConstrcutors(Class aClass, List<Member> list) {
		Constructor[] constructors = aClass.getConstructors();
		for (Constructor c : constructors) {
			list.add(c);
		}
		return constructors.length;
	}

	/**
	 * Convert the constructor to a Java Code String (arguments are replaced by
	 * the simple types)
	 * 
	 * @param c
	 *            Constructor
	 * @return
	 */
	public static String getJavaCallString(Constructor c) {
		StringBuilder call = new StringBuilder();
		call.append(c.getDeclaringClass().getSimpleName());
		addParamsString(call, c.getParameterTypes());
		return call.toString();
	}

	/**
	 * Adds the class SimpleNames, comma sepearated and surrounded by
	 * paranthesis to the call StringBuffer
	 * 
	 * @param call
	 * @param params
	 * @return
	 */
	public static StringBuilder addParamsString(StringBuilder call, Class[] params) {
		call.append("(");
		boolean firstArg = true;
		for (Class arg : params) {
			if (firstArg) {
				firstArg = false;
			} else {
				call.append(", ");
			}
			call.append(arg.getSimpleName());
		}
		call.append(")");
		return call;
	}
}
