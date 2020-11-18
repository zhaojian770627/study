package com.zj.study.freemarker.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

			listTables.add(tab);
		}

		conn.close();
		return listTables;

	}

	private static Connection getConnection(DataBase db) throws SQLException {
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
		});
	}
}
