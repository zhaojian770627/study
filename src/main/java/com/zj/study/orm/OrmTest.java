package com.zj.study.orm;

import com.zj.study.orm.anno.AnnoHelper;
import com.zj.study.orm.bean.Order;

public class OrmTest {

	public static void main(String[] args) {
		String tableName = AnnoHelper.getTableName(Order.class);
		System.out.println(tableName);
	}

}
