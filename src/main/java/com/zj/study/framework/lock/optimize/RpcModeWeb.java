package com.zj.study.framework.lock.optimize;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.zj.study.framework.lock.optimize.service.ProduceDocService;
import com.zj.study.framework.lock.optimize.service.assist.Consts;
import com.zj.study.framework.lock.optimize.service.assist.CreatePendingDocs;
import com.zj.study.framework.lock.optimize.service.assist.SL_QuestionBank;
import com.zj.study.framework.lock.optimize.vo.SrcDocVo;

public class RpcModeWeb {

	private static ExecutorService docMakeService = Executors.newFixedThreadPool(Consts.CPU_COUNT);
	private static ExecutorService docUploadService = Executors.newFixedThreadPool(Consts.CPU_COUNT);

	private static CompletionService<String> docCs = new ExecutorCompletionService<>(docMakeService);
	private static CompletionService<String> docUploadCs = new ExecutorCompletionService<>(docUploadService);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("题库开始初始化...........");
		SL_QuestionBank.initBank();
		System.out.println("题库初始化完成。");

		List<SrcDocVo> docList = CreatePendingDocs.makePendingDoc(60);
		long startTotal = System.currentTimeMillis();
		for (SrcDocVo doc : docList) {
			docCs.submit(new MakeDocTask(doc));
		}

		for (SrcDocVo doc : docList) {
			Future<String> future = docCs.take();
			docUploadCs.submit(new UploadDocTask(future.get()));
		}

		for (SrcDocVo doc : docList) {
			docUploadCs.take().get();
		}

		System.out.println("------------共耗时：" + (System.currentTimeMillis() - startTotal) + "ms-------------");
	}

	private static class MakeDocTask implements Callable<String> {
		private SrcDocVo pendingDocVO;

		public MakeDocTask(SrcDocVo pendingDocVo) {
			this.pendingDocVO = pendingDocVo;
		}

		@Override
		public String call() throws Exception {
			long start = System.currentTimeMillis();

			// String localName = ProduceDocService.makeDoc(pendingDocVO);
			// 异步生成文档
			String localName = ProduceDocService.makeDocAsyn(pendingDocVO);

			System.out.println("文档" + localName + "生成耗时：" + (System.currentTimeMillis() - start) + "ms");
			return localName;
		}
	}

	private static class UploadDocTask implements Callable<String> {
		private String filePath;

		public UploadDocTask(String filePath) {
			this.filePath = filePath;
		}

		@Override
		public String call() throws Exception {
			long start = System.currentTimeMillis();
			String remoteUrl = ProduceDocService.upLoadDoc(filePath);
			System.out.println("已上传至[" + remoteUrl + "]耗时：" + (System.currentTimeMillis() - start) + "ms");
			return remoteUrl;
		}
	}

}
