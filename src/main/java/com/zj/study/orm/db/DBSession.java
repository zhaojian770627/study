package com.zj.study.orm.db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DBSession {
	private Connection conn;

	public <T> List<T> list(Class<T> cls) throws InstantiationException, IllegalAccessException {
		List<T> list = new ArrayList<>();
		list.add(cls.newInstance());
		list.add(cls.newInstance());

		return list;
	}
}
