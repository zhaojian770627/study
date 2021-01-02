package com.zj.study.orm.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.zj.study.freemarker.entity.DataBase;
import com.zj.study.freemarker.utils.DataBaseUtils;
import com.zj.study.orm.bean.User;

public class DBSessionFactory {
	private Connection conn;

	public DBSessionFactory() throws SQLException {
//		Settings settings = new Settings("zj", "com.zj", "zj freemark test", "zj");
		DataBase db = new DataBase("MYSQL", "test");
		db.setUserName("root");
		db.setPassword("root");

		conn = DataBaseUtils.getConnection(db);
	}

	DBSession openSession() {
		return new DBSession(conn);
	}

	public static void main(String[] args) throws Exception {
		DBSessionFactory factory = new DBSessionFactory();
		List<User> list = factory.openSession().list(User.class);
		System.out.println(list);
	}
}
