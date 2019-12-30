package com.zj.study.framework.spring.cap9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zj.study.framework.spring.cap9.dao.TestDao;

@Service
public class TestService {
	@Qualifier("testDao1")
	@Autowired
	// 效果是一样的,与Autowired一样可以装配bean
	// 1,不支持Primary功能
	// 2,不支持Autowired false
	// @Resource(name="testDao2")
	private TestDao testDao;

	public void println() {
		System.out.println(testDao);
	}
}
