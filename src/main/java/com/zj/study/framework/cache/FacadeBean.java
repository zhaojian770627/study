package com.zj.study.framework.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.zj.study.framework.cache.service.Provinces;
import com.zj.study.framework.cache.service.ProvincesService;

@ManagedResource(objectName = "zj.jmx:type=FacadeBean", description = "缓存测试类")
@Component
public class FacadeBean {
	@Autowired
	ProvincesService provincesService;

	public Provinces getProvinces() {
		return provincesService.detail(1);
	}
}