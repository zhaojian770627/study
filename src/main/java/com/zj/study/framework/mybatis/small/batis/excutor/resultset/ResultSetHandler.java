package com.zj.study.framework.mybatis.small.batis.excutor.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetHandler {

	 <E> List<E> handleResultSets(ResultSet resultSet) throws SQLException, Exception;

}
