package com.zj.study.framework.lock;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumArray {
	private static class SumTask extends RecursiveTask<Integer> {

		private static final int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
		private int src[];
		private int fromIndex;
		private int toIndex;

		public SumTask(int[] src, int fromIndex, int toIndex) {
			this.src = src;
			this.fromIndex = fromIndex;
			this.toIndex = toIndex;
		}

		@Override
		protected Integer compute() {
			if (toIndex - fromIndex < THRESHOLD) {
				int total = 0;
				for (int index = fromIndex; index <= toIndex; index++) {
					total = total + src[index];
				}
				return total;
			} else {
				int mid = (toIndex + fromIndex) / 2;
				SumTask leftTask = new SumTask(src, fromIndex, mid);
				SumTask rightTask = new SumTask(src, mid + 1, toIndex);
				System.out.println(String.format("拆分left[%d]mid[%d]right[%d]", fromIndex, mid, toIndex));
				invokeAll(leftTask, rightTask);
				return leftTask.join() + rightTask.join();
			}
		}
	}

	public static void main(String args[]) {
		ForkJoinPool pool = new ForkJoinPool();
		int[] src = MakeArray.makeArray();
		long start = System.currentTimeMillis();
		SumTask sumTask = new SumTask(src, 0, src.length - 1);
		pool.invoke(sumTask);
		long v = sumTask.join();
		System.out.println("Value:" + v);
		System.out.println(((System.currentTimeMillis() - start)) / 1000);

	}
}
