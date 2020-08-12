package com.zj.study.framework.cache.service;

import java.io.Serializable;

public class Provinces implements Serializable {
	private Integer id;
	private String province;

	public Provinces(Integer id, String province) {
		super();
		this.id = id;
		this.province = province;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}
