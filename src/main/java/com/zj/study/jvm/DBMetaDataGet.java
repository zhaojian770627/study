package com.zj.study.jvm;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhaojian
 *
 */
public class DBMetaDataGet {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/test";
		Connection conn = java.sql.DriverManager.getConnection(url, "root", "root");
		DatabaseMetaData metaData = conn.getMetaData();
		System.out.println(metaData.getUserName());
		System.out.println(metaData.supportsTransactions());
		System.out.println(metaData.getDatabaseProductName());

		System.out.println("------------------------------------------------");

		ResultSet catalogs = metaData.getCatalogs();
		while (catalogs.next()) {
			System.out.println(catalogs.getString(1));
		}

		System.out.println("TABLE--------------------------------------------");
		ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
		while (tables.next()) {
			System.out.println(tables.getString("TABLE_NAME"));
		}

		System.out.println("COLUMNS--------------------------------------------");
		ResultSet columns = metaData.getColumns(null, null, "product", null);
		while (columns.next()) {
			System.out.println(columns.getString("column_name"));
		}

		catalogs.close();
		conn.close();
	}
}