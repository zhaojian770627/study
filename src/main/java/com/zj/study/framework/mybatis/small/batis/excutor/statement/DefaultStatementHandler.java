package com.zj.study.framework.mybatis.small.batis.excutor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zj.study.framework.mybatis.small.batis.config.MappedStatement;

public class DefaultStatementHandler implements StatementHandler {
	private MappedStatement mappedStatment;

	public DefaultStatementHandler(MappedStatement mappedStatement) {
		super();
		this.mappedStatment = mappedStatement;
	}

	@Override
	public PreparedStatement prepare(Connection connection) throws SQLException {
		return connection.prepareStatement(mappedStatment.getSql());
	}

	@Override
	public ResultSet query(PreparedStatement statement) throws SQLException {
		return statement.executeQuery();
	}

}
