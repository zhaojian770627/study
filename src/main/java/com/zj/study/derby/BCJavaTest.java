package com.zj.study.derby;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.impl.services.bytecode.BCJava;
import org.apache.derby.impl.services.cache.ConcurrentCacheFactory;
import org.apache.derby.impl.services.reflect.ReflectClassesJava2;

public class BCJavaTest {

	public static void main(String[] args) throws Exception {
		BCJava bcJava = new BCJava();

		setCache(bcJava);

		int modifiers = Modifier.PUBLIC | Modifier.FINAL;

		bcJava.newClassBuilder(new ReflectClassesJava2(), "a.b.c", modifiers, "ddd", null);
		System.err.println("Run success!");
	}

	private static void setCache(BCJava bcJava) throws Exception {
		ConcurrentCacheFactory cacheFactory = new ConcurrentCacheFactory();
		CacheManager cacheManager = cacheFactory.newCacheManager(bcJava, "VMTypeIdCache", 64, 256);

		Class<?> classType = bcJava.getClass();
		Field vmTypeIdCacheFeild = classType.getDeclaredField("vmTypeIdCache");
		vmTypeIdCacheFeild.setAccessible(true);
		vmTypeIdCacheFeild.set(bcJava, cacheManager);
	}

}
