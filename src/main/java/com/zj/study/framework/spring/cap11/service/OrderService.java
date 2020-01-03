package com.zj.study.framework.spring.cap11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zj.study.framework.spring.cap11.dao.OrderDao;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;

	@Transactional
	public void addOrder() {
		orderDao.insert();
//		int i=1/0;
	}
}
