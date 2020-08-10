package com.zj.study.framework.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jmx.export.MBeanExporter;

@Configurable
//@EnableCaching
@ComponentScan("com.zj.study.framework.cache")
public class MainConfig {
	@Bean
	public MBeanExporter mbeanExporter(SimpleBean simpleBean) {
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("zj:name=cacheFacade", simpleBean);
		exporter.setBeans(beans);
		return exporter;
	}
}
