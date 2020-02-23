package com.zj.study.framework.mybatis;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import org.junit.Test;

public class DriverTest {
	@Test
	public void testLoadJDBCDriver() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/test";
		Connection conn = java.sql.DriverManager.getConnection(url, "root", "root");

		Enumeration em = DriverManager.getDrivers();
		while (em.hasMoreElements()) {
			Driver d = (Driver) em.nextElement();
			System.out.println(d.getClass().getName()); // 输出驱动程序类名称
		}
	}
}
