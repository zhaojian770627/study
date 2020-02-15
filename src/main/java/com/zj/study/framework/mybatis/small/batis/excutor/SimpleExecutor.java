package com.zj.study.framework.mybatis.small.batis.excutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zj.study.framework.mybatis.small.batis.config.Configuration;
import com.zj.study.framework.mybatis.small.batis.config.MappedStatement;
import com.zj.study.framework.mybatis.small.batis.excutor.parameter.DefaultParameterHandler;
import com.zj.study.framework.mybatis.small.batis.excutor.parameter.ParameterHandler;
import com.zj.study.framework.mybatis.small.batis.excutor.resultset.DefaultResultSetHandler;
import com.zj.study.framework.mybatis.small.batis.excutor.resultset.ResultSetHandler;
import com.zj.study.framework.mybatis.small.batis.excutor.statement.DefaultStatementHandler;
import com.zj.study.framework.mybatis.small.batis.excutor.statement.StatementHandler;

public class SimpleExecutor implements Executor {
	private Configuration conf;

	public SimpleExecutor(Configuration conf) {
		this.conf = conf;
	}

	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter) throws Exception {
		// 获取mappedStatement对象，里面包含sql语句和目标对象等信息；
		MappedStatement mappedStatement = conf.getMappedStatement(ms.getSourceId());
		// 1.获取Connection对象
		Connection conn = getConnect();

		// 2.实例化StatementHandler对象，准备实例化Statement
		StatementHandler statementHandler = new DefaultStatementHandler(mappedStatement);

		// 3.通过statementHandler和Connection获取PreparedStatement
		PreparedStatement prepare = statementHandler.prepare(conn);

		// 4.实例化ParameterHandler对象，对Statement中sql语句的占位符进行处理
		ParameterHandler parameterHandler = new DefaultParameterHandler(parameter);
		parameterHandler.setParameters(prepare);

		// 5.执行查询语句，获取结果集resultSet
		ResultSet resultSet = statementHandler.query(prepare);

		// 6.实例化ResultSetHandler对象，对resultSet中的结果集进行处理，转化成目标对象
		ResultSetHandler resultSetHandler = new DefaultResultSetHandler(mappedStatement);
		return resultSetHandler.handleResultSets(resultSet);
	}

	private Connection getConnect() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName(conf.getDbDriver());
		conn = DriverManager.getConnection(conf.getDbUrl(), conf.getDbUserName(), conf.getDbPassword());
		return conn;
	}

}
