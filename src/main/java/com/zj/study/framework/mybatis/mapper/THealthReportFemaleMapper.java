package com.zj.study.framework.mybatis.mapper;

import java.util.List;

import com.zj.study.framework.mybatis.entity.THealthReportFemale;

public interface THealthReportFemaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(THealthReportFemale record);

    int insertSelective(THealthReportFemale record);

    THealthReportFemale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(THealthReportFemale record);

    int updateByPrimaryKey(THealthReportFemale record);
    
    List<THealthReportFemale> selectByUserId(Integer userID);
}