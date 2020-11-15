package com.zj.study.freemarker.entity;

import java.util.List;

public class Table {
	private String name;	// 表名称
	private String name2;	// 处理后的表名称
	private String comment; // 介绍
	private String key;		// 主键列
	private List<Column> columns;	// 列集合
}
