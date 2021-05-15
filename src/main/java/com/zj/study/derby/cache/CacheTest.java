package com.zj.study.derby.cache;

import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.impl.services.cache.ConcurrentCacheFactory;

public class CacheTest {

	public static void main(String[] args) {
		ConcurrentCacheFactory cacheFactory = new ConcurrentCacheFactory();
		CacheManager cm = cacheFactory.newCacheManager(new MyObjectFactory(), "MyObject", 64, 256);
	}

}
