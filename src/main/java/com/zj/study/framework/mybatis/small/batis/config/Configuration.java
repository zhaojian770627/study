package com.zj.study.framework.mybatis.small.batis.config;

import java.util.HashMap;
import java.util.Map;

import com.zj.study.framework.mybatis.small.batis.binding.MapperProxyFactory;
import com.zj.study.framework.mybatis.small.batis.session.SqlSession;

public class Configuration {

	public static final String DB_CONFIG_FILE = "db.properties";

	public static final String MAPPER_CONFIG_LOCATION = "smallbatismapper";

	private String dbUrl;

	private String dbUserName;

	private String dbPassword;

	private String dbDriver;

	// mapper xml解析完以后select节点的信息存放在mappedStatements
	protected final Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public Map<String, MappedStatement> getMappedStatements() {
		return mappedStatements;
	}
	
	public MappedStatement getMappedStatement(String sourceId) {
		return mappedStatements.get(sourceId);
	}

	@Override
	public String toString() {
		return "Configuration [dbUrl=" + dbUrl + ", dbUserName=" + dbUserName + ", dbPassword=" + dbPassword
				+ ", dbDriver=" + dbDriver + ", mappedStatements=" + mappedStatements + "]";
	}

	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		return MapperProxyFactory.getMapperProxy(sqlSession, type);
	}
}
