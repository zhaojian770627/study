package com.zj.study.framework.spring.lock.redisson.task;

import java.io.Serializable;
import java.util.UUID;

public class TaskContext implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String id;
	String name;
	String desc;

	public TaskContext(String name, String desc) {
		id = UUID.randomUUID().toString();
		this.name = name;
		this.desc = desc;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
