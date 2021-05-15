package com.zj.study.derby.cache;

import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.cache.CacheableFactory;

public class MyObjectFactory implements CacheableFactory {

	@Override
	public Cacheable newCacheable(CacheManager cm) {
		return new MyObject();
	}

}
