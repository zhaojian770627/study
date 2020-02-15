package com.zj.study.framework.mybatis.small.batis.excutor;

import java.util.List;

import com.zj.study.framework.mybatis.small.batis.config.MappedStatement;

public interface Executor {

	<E> List<E> query(MappedStatement ms, Object parameter) throws Exception;

}
