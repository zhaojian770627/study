package com.zj.study.framework.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zj.study.framework.mybatis.entity.TUser;

public interface TUserMapper {

	TUser selectByPrimaryKey(Integer id);

	int insert1(TUser record);

	int insert2(TUser record);

	List<TUser> selectBySymbol(@Param("tableName") String tableName, @Param("inCol") String inCol,
			@Param("orderStr") String orderStr, @Param("sex") Byte sex);

	List<TUser> selectIfOper(@Param("email") String email, @Param("sex") Byte sex);

	List<TUser> selectIfandWhereOper(@Param("email") String email, @Param("sex") Byte sex);

	int updateIfOper(TUser record);

	int updateIfAndSetOper(TUser record);

	int insertIfOper(TUser record);
	
    int insertSelective(TUser record);
    
    List<TUser> selectForeach4In(String[] names);
    
    int insertForeach4Batch(List<TUser> users);
    
    List<TUser> selectUserPosition1();
    
    List<TUser> selectUserPosition2();
}