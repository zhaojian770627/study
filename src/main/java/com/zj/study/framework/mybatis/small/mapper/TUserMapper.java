package com.zj.study.framework.mybatis.small.mapper;

import java.util.List;

import com.zj.study.framework.mybatis.small.entity.TUser;

public interface TUserMapper {

	TUser selectByPrimaryKey(int i);

	List<TUser> selectAll();

}
