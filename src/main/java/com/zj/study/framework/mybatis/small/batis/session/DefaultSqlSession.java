package com.zj.study.framework.mybatis.small.batis.session;

import java.sql.SQLException;
import java.util.List;

import com.zj.study.framework.mybatis.small.batis.config.Configuration;
import com.zj.study.framework.mybatis.small.batis.config.MappedStatement;
import com.zj.study.framework.mybatis.small.batis.excutor.Executor;
import com.zj.study.framework.mybatis.small.batis.excutor.SimpleExecutor;

public class DefaultSqlSession implements SqlSession {

	// 配置对象全局唯一 加载数据库信息和mapper文件信息
	private Configuration conf;

	// 真正提供数据库访问能力的对象
	private Executor executor;

	public DefaultSqlSession(Configuration conf) {
		super();
		this.conf = conf;
		
		executor = new SimpleExecutor(conf);
	}

	@Override
	public <T> T getMapper(Class<T> type) {
		return conf.<T>getMapper(type, this);
	}

	@Override
	public <T> T selectOne(String statement, Object parameter) throws SQLException, ClassNotFoundException {
		List<Object> selectList = this.selectList(statement, parameter);
		if (selectList != null && selectList.size() > 0) {
			return (T) selectList.get(0);
		}
		return null;
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter) throws SQLException, ClassNotFoundException {
		MappedStatement mappedStatement = conf.getMappedStatement(statement);
		return executor.query(mappedStatement, parameter);
	}

}
