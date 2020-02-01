package com.zj.study.framework.mybatis.mapper;

import java.util.List;

import com.zj.study.framework.mybatis.entity.THealthReportMale;

public interface THealthReportMaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(THealthReportMale record);

    int insertSelective(THealthReportMale record);

    THealthReportMale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(THealthReportMale record);

    int updateByPrimaryKey(THealthReportMale record);
    
    List<THealthReportMale> selectByUserId(Integer userID);
}