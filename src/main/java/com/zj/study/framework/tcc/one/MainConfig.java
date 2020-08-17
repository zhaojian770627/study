package com.zj.study.framework.tcc.one;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.MBeanExporter;

@Configurable
public class MainConfig {
	@Bean
	public MBeanExporter mbeanExporter(FacadeBean simpleBean) {
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("zj:name=cacheFacade", simpleBean);
		exporter.setBeans(beans);
		return exporter;
	}
}
