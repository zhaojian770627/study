package com.zj.study.orm.dao.base;

public interface Dao<T> {
	public int save(T obj);

	public int update(T obj);

	public int delete(T obj);

}
