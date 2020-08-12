package com.zj.study.framework.cache.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * springcache优雅实现缓存
 */
@Service("provincesService")
@CacheConfig(cacheNames = "province") // 通用配置
public class ProvincesServiceImpl1 implements ProvincesService {

	/**
	 * 查询数据时，使用
	 * 
	 * @param provinceid
	 * @return
	 */
	@Cacheable
	public Provinces detail(int provinceid) {// 一个接口方法，对应一个缓存器
		Provinces provinces = null;
		System.out.println("数据库中得到数据--------" + System.currentTimeMillis());
		provinces = new Provinces(1, "a");
		return provinces;
	}

}