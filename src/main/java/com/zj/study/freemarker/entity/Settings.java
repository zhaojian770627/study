package com.zj.study.freemarker.entity;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;

public class Settings {
	private String project = "example";
	private String pPackage = "com.example.demo";
	private String projectComent;
	private String author;
	private String pathAll;
	private String[] paths;

	public Settings(String project, String pPackage, String projectComent, String author) {
		super();
		if (StringUtils.isNotBlank(project))
			this.project = project;

		if (StringUtils.isNotBlank(pPackage))
			this.pPackage = pPackage;
		this.projectComent = projectComent;
		this.author = author;

		paths = pPackage.split("\\.");
		pathAll = pPackage.replace(".", "/");
	}

}
