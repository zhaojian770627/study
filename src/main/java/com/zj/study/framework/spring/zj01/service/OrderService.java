package com.zj.study.framework.spring.zj01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zj.study.framework.spring.zj01.dao.IOrderDao;

@Service
public class OrderService {
	@Autowired
	private IOrderDao orderDao;

	@Transactional
	public void addOrder() {
		orderDao.insert();
	}
}
