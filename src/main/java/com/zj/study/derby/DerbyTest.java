package com.zj.study.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyTest {


	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		Connection conn = DriverManager.getConnection("jdbc:derby:d://data//derbydb;create=true");
//		Connection conn = DriverManager.getConnection("jdbc:derby:derbydb");
		
//		createDB(conn);
		
		PreparedStatement pstate1 = conn.prepareStatement("select * from derbytable where id = ?");
		pstate1.setInt(1, 2);
		ResultSet rset1 = pstate1.executeQuery();
		while(rset1.next()) {
			System.out.println(rset1.getInt(1)+">"+rset1.getString(2));
		}
		pstate1.close();
		
		conn.close();
	}

	private static void createDB(Connection conn) throws SQLException {
		Statement state = conn.createStatement();
		state.executeUpdate("create table derbytable(id int,val varchar(128))");
		state.close();
		
		Statement state2 = conn.createStatement();
		state2.executeUpdate("insert into derbytable values (1,'tom') ");
		state2.executeUpdate("insert into derbytable values (2,'jerry') ");
		state2.close();
	}

}
