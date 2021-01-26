package com.zj.study.framework.spring.lock.config;

import java.util.UUID;

public class VerInfo {
	final String serverFlag;

	public VerInfo() {
		serverFlag = UUID.randomUUID().toString();
	}

	public String getServerFlag() {
		return serverFlag;
	}
}
