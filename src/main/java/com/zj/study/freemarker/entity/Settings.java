package com.zj.study.freemarker.entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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

	public Map<String, Object> getSettingMap() {
		Map<String, Object> map = new HashMap<>();
		Field[] declaredFields = Settings.class.getFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(this));
			} catch (Exception e) {

			}
		} 
		return map;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getpPackage() {
		return pPackage;
	}

	public void setpPackage(String pPackage) {
		this.pPackage = pPackage;
	}

	public String getProjectComent() {
		return projectComent;
	}

	public void setProjectComent(String projectComent) {
		this.projectComent = projectComent;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String[] getPaths() {
		return paths;
	}

	public void setPaths(String[] paths) {
		this.paths = paths;
	}
}
