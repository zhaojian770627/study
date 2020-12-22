package com.zj.study.orm.anno;

import java.lang.reflect.Field;

public class AnnoHelper {
	public static String getTableName(Class<?> beanCls) {
		Table tabAnno = beanCls.getAnnotation(Table.class);
		if (tabAnno == null)
			return beanCls.getSimpleName().toLowerCase();
		else
			return tabAnno.value();
	}

	public static String getColumnName(Field field) {
		Column colAnno = field.getAnnotation(Column.class);
		if (colAnno == null)
			return field.getName().toLowerCase();
		else
			return colAnno.value();
	}
}
