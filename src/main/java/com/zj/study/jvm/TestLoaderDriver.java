package com.zj.study.jvm;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhaojian
 *
 */
public class TestLoaderDriver {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/test";
		Connection conn = java.sql.DriverManager.getConnection(url, "root", "root");
		System.out.println(conn);
	}
}