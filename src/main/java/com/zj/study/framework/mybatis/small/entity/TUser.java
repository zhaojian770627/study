package com.zj.study.framework.mybatis.small.entity;

import java.io.Serializable;

public class TUser implements Serializable {

	private Integer id;

	private String user_name;

	private String real_name;

	private String mobile;

	private String email;

	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	@Override
	public String toString() {
		return "TUser [id=" + id + ", user_name=" + user_name + ", real_name=" + real_name + ", mobile=" + mobile
				+ ", email=" + email + ", note=" + note + "]";
	}

}