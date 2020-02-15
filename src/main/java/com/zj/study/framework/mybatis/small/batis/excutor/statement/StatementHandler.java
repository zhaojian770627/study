package com.zj.study.framework.mybatis.small.batis.excutor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface StatementHandler {

	// 从连接中获取一个Statement
	PreparedStatement prepare(Connection connection) throws SQLException;

	// 执行select语句
	ResultSet query(PreparedStatement statement) throws SQLException;

}
