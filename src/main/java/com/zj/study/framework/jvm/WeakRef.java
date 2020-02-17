package com.zj.study.framework.jvm;

import java.lang.ref.WeakReference;

/**
 * 测试弱引用
 * 
 * @author zhaojian
 *
 */
public class WeakRef {

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
		WeakReference<User> userWeak = new WeakReference<User>(u);
		u = null;

		System.out.println(userWeak.get());
		System.gc();
		System.out.println("After GC");
		System.out.println(userWeak.get());
	}
}
