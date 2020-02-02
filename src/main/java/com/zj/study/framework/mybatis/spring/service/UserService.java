package com.zj.study.framework.mybatis.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zj.study.framework.mybatis.entity.TUser;
import com.zj.study.framework.mybatis.mapper.TUserMapper;

@Service
public class UserService {
	@Resource
	private TUserMapper userMapper;

	public TUser getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	public void addUser(TUser user) {
		userMapper.insert1(user);
	}
}
