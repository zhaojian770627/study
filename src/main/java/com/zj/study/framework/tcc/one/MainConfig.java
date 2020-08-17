package com.zj.study.framework.tcc.one;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.MBeanExporter;

public class MainConfig {
	public MainConfig() {
		System.out.println("abcd");
	}

	@Bean
	public MBeanExporter mbeanExporter(FacadeBean simpleBean) {
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("zj:name=FacadeBean", simpleBean);
		exporter.setBeans(beans);
		return exporter;
	}

	@Bean
	public FacadeBean facadeBean() {
		return new FacadeBean();
	}
}
