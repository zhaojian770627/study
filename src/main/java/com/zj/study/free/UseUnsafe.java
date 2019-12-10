package com.zj.study.free;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UseUnsafe {
	static final int SHARED_SHIFT = 16;
	static final int SHARED_UNIT = (1 << SHARED_SHIFT);
	static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
	static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

	public static Unsafe getUnsafe() {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			return (Unsafe) f.get(null);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		Unsafe unsafe = getUnsafe();
	}

}
