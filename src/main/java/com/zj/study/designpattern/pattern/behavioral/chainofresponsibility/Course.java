package com.zj.study.designpattern.pattern.behavioral.chainofresponsibility;

public class Course {

	private String name;
	private String article;
	private String video;

	public void setName(String name) {
		this.name = name;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getName() {
		return name;
	}

	public String getArticle() {
		return article;
	}

	public String getVideo() {
		return video;
	}

}
