package com.zj.study.framework.mybatis.entity;

import java.util.List;

public class TUser {
	private Integer id;

	private String userName;

	private String realName;

	private Byte sex;

	private String mobile;

	private String email;

	private String note;

	private TPosition position;

	private List<TJobHistory> jobs;

	// public TUser(Integer id, String userName) {
	// super();
	// this.id = id;
	// this.userName = userName;
	// }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
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

	public TPosition getPosition() {
		return position;
	}

	public void setPosition(TPosition position) {
		this.position = position;
	}

	public List<TJobHistory> getJobs() {
		return jobs;
	}

	public void setJobs(List<TJobHistory> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		String positionId = (position == null ? "" : String.valueOf(position.getId()));
		return "TUser [id=" + id + ", userName=" + userName + ", realName=" + realName + ", sex=" + sex + ", mobile="
				+ mobile + ", email=" + email + ", note=" + note + ", positionId=" + positionId + "]";
	}

}