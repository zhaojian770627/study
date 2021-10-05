package com.zj.study.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DerbySelect {
	Connection conn;

	public static void main(String[] args) throws Exception {
//		SanityManager.DEBUG_SET("ClassLineNumbers");
//		SanityManager.DEBUG_SET("DumpClassFile");
//		SanityManager.DEBUG_SET("ByteCodeGenInstr");
//		SanityManager.DEBUG_SET("DumpParseTree");
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		DerbySelect select = new DerbySelect();
		select.selectData();
		select.close();
	}

	public DerbySelect() throws SQLException {
		conn = DriverManager.getConnection("jdbc:derby:d://data//debugdb;create=true");
	}

	private void selectData() throws SQLException {
		PreparedStatement pstate1 = conn.prepareStatement("select * from derbytable where id = ? and val= ?");
		pstate1.setInt(1, 1);
		pstate1.setString(2, "tom");
		ResultSet rset1 = pstate1.executeQuery();
		while (rset1.next()) {
			System.out.println(rset1.getString("val"));
		}
		rset1.close();
		pstate1.close();
	}

	private void close() throws SQLException {
		if (conn != null)
			conn.close();
	}
}
