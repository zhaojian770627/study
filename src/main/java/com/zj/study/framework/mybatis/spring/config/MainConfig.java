package com.zj.study.framework.mybatis.spring.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.alibaba.druid.pool.DruidDataSource;

//配置类====配置文件
@Configuration
@PropertySource(value = "classpath:/db.properties")
public class MainConfig {

	// ---------------数据源相关---------------------
	@Value("${jdbc_url}")
	String jdbc_url;
	@Value("${jdbc_driver}")
	String jdbc_driver;
	@Value("${jdbc_username}")
	String jdbc_username;
	@Value("${jdbc_password}")
	String jdbc_password;

	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(jdbc_driver);
		dataSource.setUrl(jdbc_url);
		dataSource.setUsername(jdbc_username);
		dataSource.setPassword(jdbc_password);
		// 配置初始化大小、最小、最大
		dataSource.setInitialSize(1);
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(1);
		// 配置获取连接等待超时的时间
		dataSource.setMaxWait(60000);
		// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		// 配置一个连接在池中最小生存的时间，单位是毫秒
		dataSource.setMinEvictableIdleTimeMillis(300000);

		dataSource.setValidationQuery("SELECT 'x'");
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);

		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		dataSource.setFilters("stat");
		return dataSource;
	}

	@Bean
	public Resource[] sqlMapper() {
		ClassPathResource resource = new ClassPathResource("classpath*:sqlmapper/*.xml");
		return new Resource[] { resource };
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource,
			@Autowired Resource[] sqlMapper) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.zj.study.framework.mybatis.entity");
		sqlSessionFactoryBean.setMapperLocations(sqlMapper);
		return sqlSessionFactoryBean;
	}
}
