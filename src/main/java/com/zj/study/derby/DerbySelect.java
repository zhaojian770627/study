package com.zj.study.derby;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.shared.common.sanity.SanityManager;

public class DerbySelect {

	public static void main(String[] args) throws Exception {
//		SanityManager.DEBUG_SET("ClassLineNumbers");
//		SanityManager.DEBUG_SET("DumpClassFile");
//		SanityManager.DEBUG_SET("ByteCodeGenInstr");
		SanityManager.DEBUG_SET("DumpParseTree");
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

		Connection conn = DriverManager.getConnection("jdbc:derby:d://data//debugdb;create=true");

		selectData(conn);
		conn.close();
	}

	private static void selectData(Connection conn) throws SQLException {
		PreparedStatement pstate1 = conn.prepareStatement("select * from derbytable where id = ?");
		pstate1.setInt(1, 1);
		ResultSet rset1 = pstate1.executeQuery();
		while (rset1.next()) {
			System.out.println(rset1.getInt(1) + ">" + rset1.getString(2));
		}
		rset1.close();
		pstate1.close();

	}

	private static void deleteDB() {
		File file = new File("d://data//debugdb");
		deleteFile(file);
	}

	private static void insertData(Connection conn) throws SQLException {
		conn.setAutoCommit(false);
		Statement state2 = conn.createStatement();
		state2.executeUpdate("insert into derbytable values (1,'tom') ");
//		state2.executeUpdate("insert into derbytable values (2,'jerry') ");
		state2.close();
//		conn.rollback();
		conn.commit();
		conn.setAutoCommit(true);
	}

	private static void createTable(Connection conn) throws SQLException {
		Statement state = conn.createStatement();
		state.executeUpdate("create table derbytable(id int,val varchar(128))");
		state.close();
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
