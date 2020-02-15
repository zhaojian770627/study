package com.zj.study.framework.mybatis.small.batis.excutor.resultset;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zj.study.framework.mybatis.small.batis.config.MappedStatement;
import com.zj.study.framework.mybatis.small.batis.reflection.ReflectionUtil;

public class DefaultResultSetHandler implements ResultSetHandler {

	private MappedStatement mappedStament;

	public DefaultResultSetHandler(MappedStatement mappedStatement) {
		super();
		this.mappedStament = mappedStatement;
	}

	@Override
	public <E> List<E> handleResultSets(ResultSet resultSet) throws Exception {
		List<E> ret = new ArrayList<E>();
		while (resultSet.next()) {
			Class<?> entityClass;
			entityClass = Class.forName(mappedStament.getResultType());
			E entity = (E) entityClass.newInstance();
			Field[] declaredFields = entityClass.getDeclaredFields();
			for (int i = 0; i < declaredFields.length; i++) {
				if (declaredFields[i].getType().getSimpleName().equals("String")) {
					// ReflectionUtil.setPropToBean(entity, declaredFields[0].getName(),
					// resultSet.getString(resultSet.findColumn(declaredFields[0].getName())));
					ReflectionUtil.setPropToBean(entity, declaredFields[i].getName(),
							resultSet.getString(declaredFields[i].getName()));
				} else if (declaredFields[i].getType().getSimpleName().equals("Integer")) {
					ReflectionUtil.setPropToBean(entity, declaredFields[i].getName(),
							resultSet.getInt(declaredFields[i].getName()));

				}
			}
			ret.add(entity);
		}
		return ret;
	}

}
