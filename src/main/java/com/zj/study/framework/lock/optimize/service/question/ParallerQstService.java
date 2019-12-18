package com.zj.study.framework.lock.optimize.service.question;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.zj.study.framework.lock.optimize.service.assist.Consts;
import com.zj.study.framework.lock.optimize.service.assist.SL_QuestionBank;
import com.zj.study.framework.lock.optimize.vo.QuestionInCacheVo;
import com.zj.study.framework.lock.optimize.vo.QuestionInDBVo;
import com.zj.study.framework.lock.optimize.vo.TaskResultVo;

public class ParallerQstService {
	private static ConcurrentHashMap<Integer, QuestionInCacheVo> questionCache = new ConcurrentHashMap<>();

	private static ConcurrentHashMap<Integer, Future<QuestionInCacheVo>> processingQuestionCache = new ConcurrentHashMap<>();

	private static ExecutorService makeQuestionService = Executors.newFixedThreadPool(Consts.CPU_COUNT * 2);

	public static TaskResultVo makeQuestion(Integer questionId) {
		QuestionInCacheVo qstCacheVo = questionCache.get(questionId);
		if (null == qstCacheVo) {
			System.out.println(".....题目[" + questionId + "]在缓存中不存在。准备启动任务");
			return new TaskResultVo(getQstFuture(questionId));
		} else {
			String questionSha = SL_QuestionBank.getQuetion(questionId).getSha();
			if (questionSha.equals(qstCacheVo.getQuestionSha())) {
				System.out.println("........题目[" + questionId + "]在缓存中已存在，且未变化。");
				return new TaskResultVo(qstCacheVo.getQuestionDetail());
			} else {
				System.out.println(".....题目[" + questionId + "]在缓存中已存在.但是发生了变化，更新缓存.");
				return new TaskResultVo(getQstFuture(questionId));
			}
		}
	}

	private static Future<QuestionInCacheVo> getQstFuture(Integer questionId) {
		Future<QuestionInCacheVo> questionFuture = processingQuestionCache.get(questionId);
		try {
			if (questionFuture == null) {
				QuestionInDBVo qstDbVo = SL_QuestionBank.getQuetion(questionId);
				QuestionTask questionTask = new QuestionTask(qstDbVo, questionId);

				// makeQuestionService.submit(questionTask);
				// processingQuestionCache.putIfAbsent(questionId, questionFuture);

				FutureTask<QuestionInCacheVo> ft = new FutureTask<>(questionTask);
				questionFuture = processingQuestionCache.putIfAbsent(questionId, ft);
				if (null == questionFuture) {
					questionFuture = ft;
					makeQuestionService.execute(ft);
					System.out.println("成功启动了题目[" + questionId + "]的计算任务，请等待完成>>>>>>>>>");
				} else {
					System.out.println("<<<<<<<<<<<有其他线程刚刚启动了题目[" + questionId + "]的计算任务，本任务无需开启!");
				}

			} else {
				System.out.println("题目[" + questionId + "]已存在计算任务，无需重新生成。");
			}
		} catch (Exception e) {
			processingQuestionCache.remove(questionId);
			e.printStackTrace();
			throw e;
		}
		return questionFuture;
	}

	private static class QuestionTask implements Callable<QuestionInCacheVo> {

		private QuestionInDBVo qstDbVo;
		private Integer questionId;

		public QuestionTask(QuestionInDBVo qstDbVo, Integer questionId) {
			super();
			this.qstDbVo = qstDbVo;
			this.questionId = questionId;
		}

		@Override
		public QuestionInCacheVo call() throws Exception {
			try {
				String qstDetail = BaseQuestionProcessor.makeQuestion(questionId,
						SL_QuestionBank.getQuetion(questionId).getDetail());
				String questionSha = qstDbVo.getSha();
				QuestionInCacheVo qstCache = new QuestionInCacheVo(qstDetail, questionSha);
				return qstCache;
			} finally {
				processingQuestionCache.remove(questionId);
			}
		}

	}
}
