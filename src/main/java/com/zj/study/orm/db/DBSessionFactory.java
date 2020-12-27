package com.zj.study.orm.db;

import java.util.List;

import com.zj.study.orm.bean.User;

public class DBSessionFactory {
	DBSession openSession() {
		return new DBSession();
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		DBSessionFactory factory = new DBSessionFactory();
		List<User> list = factory.openSession().list(User.class);
		System.out.println(list.size());
	}
}
