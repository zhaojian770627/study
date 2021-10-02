package com.zj.study.derby.lock;

public class LockGroup implements ILockGroup {

	private final String name;

	public LockGroup(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
