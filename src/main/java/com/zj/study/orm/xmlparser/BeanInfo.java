package com.zj.study.orm.xmlparser;

import java.util.List;

public class BeanInfo {
	private String id;
	private String clazz;
	private String scope;

	private List<PropsInfo> props;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public List<PropsInfo> getProps() {
		return props;
	}

	public void setProps(List<PropsInfo> props) {
		this.props = props;
	}
}
