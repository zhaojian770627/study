package com.zj.study.orm.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zj.study.orm.anno.AnnoHelper;

public class DBSession {
	private Connection conn;

	public DBSession(Connection conn) {
		this.conn = conn;
	}

	public <T> List<T> list(Class<T> cls) throws Exception {
		String sql = "select %s from %s";

		StringBuilder columns = new StringBuilder();
		Field[] fs = cls.getDeclaredFields();
		for (int i = 0, len = fs.length; i < len; i++) {
			columns.append(AnnoHelper.getColumnName(fs[i]));

			if (i != len - 1) {
				columns.append(",");
			}
		}

		sql = String.format(sql, columns.toString(), AnnoHelper.getTableName(cls));
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<T> list = new ArrayList<>();
		T obj = null;
		while (rs.next()) {
			obj = cls.newInstance();

			for (Field f : fs) {
				f.setAccessible(true);
				String fName = AnnoHelper.getColumnName(f);
				Class<?> type = f.getType();
				if (type == String.class) {
					f.set(obj, rs.getString(fName));
				} else if (type == int.class || type == Integer.class) {
					f.set(obj, rs.getInt(fName));
				} else if (type == double.class || type == Double.class) {
					f.set(obj, rs.getDouble(fName));
				} else if (type == Date.class) {
					f.set(obj, rs.getDate(fName));
				}
			}
			list.add(obj);
		}
		stmt.close();
		return list;
	}
}
