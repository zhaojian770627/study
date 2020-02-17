package com.zj.study.framework.jvm;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

/**
 * 测试软引用
 * 
 * -Xmx10m -Xms10m -XX:+PrintGC
 * 
 * @author zhaojian
 *
 */
public class SoftRef {

	public static class User {
		public int id = 0;
		public String name = "";

		public User(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		}
	}

	public static void main(String[] args) {
		User u = new User(1, "zj");
		SoftReference<User> userSoft = new SoftReference<SoftRef.User>(u);
		u = null;

		System.out.println(userSoft.get());
		System.gc();
		System.out.println("After GC");
		System.out.println(userSoft.get());
		
		List<byte[]> list = new LinkedList<>();
		try {
			for (int i = 0; i < 100; i++) {
				System.out.println("****************" + userSoft.get());
				list.add(new byte[1024 * 1024]);
			}
		} catch (Throwable e) {
			System.out.println("****************" + userSoft.get());
		}
	}

}
