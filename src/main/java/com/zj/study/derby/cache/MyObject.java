package com.zj.study.derby.cache;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.services.cache.Cacheable;

public class MyObject implements Cacheable {
	String value;

	@Override
	public Cacheable setIdentity(Object key) throws StandardException {
		System.err.println("setIdentity:" + key);
		this.value = key.toString();
		return this;
	}

	@Override
	public Cacheable createIdentity(Object key, Object createParameter) throws StandardException {
		System.err.println("createIdentity:" + key);
		this.value = key.toString();
		return this;
	}

	@Override
	public void clearIdentity() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getIdentity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clean(boolean forRemove) throws StandardException {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "MyObject [value=" + value + "]";
	}
}
