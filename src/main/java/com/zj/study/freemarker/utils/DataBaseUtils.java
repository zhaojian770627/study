package com.zj.study.freemarker.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zj.study.freemarker.entity.Column;
import com.zj.study.freemarker.entity.DataBase;
import com.zj.study.freemarker.entity.Table;

public class DataBaseUtils {
	public static List<Table> getDBTables(DataBase db) throws SQLException {
		Connection conn = getConnection(db);
		List<Table> listTables = new ArrayList<>();

		DatabaseMetaData metaData = conn.getMetaData();

		ResultSet tablesRs = metaData.getTables(null, null, null, new String[] { "TABLE" });
		while (tablesRs.next()) {
			Table tab = new Table();

			String tableName = tablesRs.getString("TABLE_NAME");

			String className = tablesRs.getString("TABLE_NAME");
			String remarks = tablesRs.getString("REMARKS");

			ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
			String keys = "";
			while (primaryKeys.next()) {
				String keyName = primaryKeys.getString("COLUMN_NAME");
				keys += keys + keyName + ",";
			}
			keys = keys.substring(0, keys.length() - 1);

			tab.setName(tableName);
			tab.setName2(className);
			tab.setComment(remarks);
			tab.setKey(keys);

			// 处理表中所有字段
			ResultSet columns = metaData.getColumns(null, null, tableName, null);

			List<Column> columnList = new ArrayList<Column>();
			while (columns.next()) {
				Column cn = new Column();

				String columnName = columns.getString("COLUMN_NAME");
				cn.setColumnName(columnName);

				String attrName = StringUtils.toJavaVariableName(columnName);
				cn.setColumnName2(attrName);

				String dbType = columns.getString("TYPE_NAME");
				cn.setColumnDbType(dbType);

				String javaType = PropertiesUtils.customMap.get(dbType);
				cn.setColumnType(javaType);

				String columnRemark = columns.getString("REMARKS");
				cn.setColumnComment(columnRemark);

				String pri = null;
				if (StringUtils.contains(columnName, keys.split(","))) {
					pri = "PRI";
				}
				cn.setColumnKey(pri);
				columnList.add(cn);
			}
			columns.close();
			tab.setColumns(columnList);
			listTables.add(tab);
		}
		tablesRs.close();
		conn.close();
		return listTables;

	}

	public static Connection getConnection(DataBase db) throws SQLException {
		Connection conn = java.sql.DriverManager.getConnection(db.getUrl(), db.getUserName(), db.getPassword());
		return conn;
	}

	public static void main(String[] args) throws SQLException {
		DataBase db = new DataBase("MYSQL", "test");
		db.setUserName("root");
		db.setPassword("root");

		List<Table> dbTables = getDBTables(db);
		dbTables.forEach(t -> {
			System.out.println(t.getKey());
			List<Column> columns = t.getColumns();
			columns.forEach(c -> {
				System.out.println(c.toString());
			});
		});
	}
}
