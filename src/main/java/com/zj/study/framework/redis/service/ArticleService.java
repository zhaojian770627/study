package com.zj.study.framework.redis.service;

import java.util.List;
import java.util.Map;

public interface ArticleService {
	
	List<Map> queryArticleVoteByPostTime(String articleId);
}