package com.zj.study.framework.mybatis.mapper;

import com.zj.study.framework.mybatis.entity.TPosition;

public interface TPositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPosition record);

    int insertSelective(TPosition record);

    TPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPosition record);

    int updateByPrimaryKey(TPosition record);
}