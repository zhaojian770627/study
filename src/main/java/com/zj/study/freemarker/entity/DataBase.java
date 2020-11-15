package com.zj.study.freemarker.entity;

public class DataBase {
	private static String mysqlUrl = "jdbc:mysql://%s:%s/%s";
	private static String oracleUrl = "jdbc:oracle:thin:@%s:%s/%s";

	private String dbType;
	private String driver;
	private String userName;
	private String password;
	private String url;

	public DataBase() {

	}

	public DataBase(String dbType) {
		this(dbType, "127.0.0.1", "3306", "");
	}

	public DataBase(String dbType,String db) {
		this(dbType, "127.0.0.1", "3306", db);
	}

	public DataBase(String dbType, String ip, String port, String db) {
		this.dbType = dbType;
		if ("MYSQL".equals(dbType.toUpperCase())) {
			this.driver = "com.mysql.jdbc.Driver";
			this.url = String.format(mysqlUrl, ip, port, db);
		} else {
			this.driver = "oracle.jdbc.driver.OracleDriver";
			this.url = String.format(oracleUrl, ip, port, db);
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
