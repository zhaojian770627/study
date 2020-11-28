package com.zj.study.freemarker.utils;

public class StringUtils {

	public static String toJavaVariableName(String columnName) {
		return columnName;
	}

	public static boolean contains(String columnName, String[] split) {
		for (String s : split) {
			if (s.equals(columnName))
				return true;
		}
		return false;
	}

}
