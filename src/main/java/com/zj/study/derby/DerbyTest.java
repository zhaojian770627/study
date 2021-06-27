package com.zj.study.derby;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.shared.common.sanity.SanityManager;

public class DerbyTest {

	public static void main(String[] args) throws Exception {
//		SanityManager.DEBUG_SET("ClassLineNumbers");
//		SanityManager.DEBUG_SET("DumpClassFile");
//		SanityManager.DEBUG_SET("ByteCodeGenInstr");
		SanityManager.DEBUG_SET("DumpParseTree");
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		deleteDB();

		// org.apache.derby.impl.jdbc.EmbedConnection
		Connection conn = DriverManager.getConnection("jdbc:derby:d://data//debugdb;create=true");

		/*
		createTable(conn);

		PreparedStatement pstate1 = conn.prepareStatement("select * from derbytable where id = ?");
		pstate1.setInt(1, 2);
		ResultSet rset1 = pstate1.executeQuery();
		while (rset1.next()) {
			System.out.println(rset1.getInt(1) + ">" + rset1.getString(2));
		}
		pstate1.close();
		 */
		conn.close();
	}

	private static void deleteDB() {
		File file = new File("d://data//derbydb");
		deleteFile(file);
	}

	private static void createTable(Connection conn) throws SQLException {
		Statement state = conn.createStatement();
		state.executeUpdate("create table derbytable(id int,val varchar(128))");
		state.close();

		Statement state2 = conn.createStatement();
		state2.executeUpdate("insert into derbytable values (1,'tom') ");
		state2.executeUpdate("insert into derbytable values (2,'jerry') ");
		state2.close();
	}

	public static boolean deleteFile(File dirFile) {
		// 如果dir对应的文件不存在，则退出
		if (!dirFile.exists()) {
			return false;
		}

		if (dirFile.isFile()) {
			return dirFile.delete();
		} else {

			for (File file : dirFile.listFiles()) {
				deleteFile(file);
			}
		}

		return dirFile.delete();
	}

}
