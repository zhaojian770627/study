package com.zj.study.framework.mybatis.mapper;

import com.zj.study.framework.mybatis.entity.TUser;

public interface TUserMapper {
	
    TUser selectByPrimaryKey(Integer id);
    
    int insert1(TUser record);
}