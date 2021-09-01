package com.zj.study.derby;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
