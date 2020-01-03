package com.zj.study.framework.spring.cap11.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert() {
		String sql = "insert into myorder(orderno,money) values(?,?)";
		jdbcTemplate.update(sql, "001", 100);
	}
}
