package com.zj.study.framework.mybatis.small.batis.excutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.zj.study.framework.mybatis.small.batis.config.Configuration;
import com.zj.study.framework.mybatis.small.batis.config.MappedStatement;

public class SimpleExecutor implements Executor {
	private Configuration conf;

	public SimpleExecutor(Configuration conf) {
		this.conf = conf;
	}

	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException, ClassNotFoundException {
		// 获取mappedStatement对象，里面包含sql语句和目标对象等信息；
		MappedStatement mappedStatement = conf.getMappedStatement(ms.getSourceId());
		// 1.获取Connection对象
		Connection conn = getConnect();
		return null;
	}

	private Connection getConnect() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName(conf.getDbDriver());
		conn = DriverManager.getConnection(conf.getDbUrl(), conf.getDbUserName(), conf.getDbPassword());
		return conn;
	}

}
