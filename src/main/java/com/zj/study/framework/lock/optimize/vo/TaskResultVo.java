package com.zj.study.framework.lock.optimize.vo;

import java.util.concurrent.Future;

public class TaskResultVo {
	private final String questionDetail;
	private final Future<QuestionInCacheVo> questionFutrue;

	public TaskResultVo(String questionDetail) {
		super();
		this.questionDetail = questionDetail;
		questionFutrue = null;
	}

	public TaskResultVo(Future<QuestionInCacheVo> questionFutrue) {
		super();
		questionDetail = null;
		this.questionFutrue = questionFutrue;
	}

	public String getQuestionDetail() {
		return questionDetail;
	}

	public Future<QuestionInCacheVo> getQuestionFutrue() {
		return questionFutrue;
	}
}
