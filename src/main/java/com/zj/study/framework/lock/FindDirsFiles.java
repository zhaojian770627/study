package com.zj.study.framework.lock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class FindDirsFiles extends RecursiveAction {
	File path;

	public FindDirsFiles(File file) {
		this.path = file;
	}

	public File getPath() {
		return path;
	}

	public void setPath(File path) {
		this.path = path;
	}

	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		FindDirsFiles task = new FindDirsFiles(new File("D:\\app"));
		pool.execute(task);

		System.out.println("Task is running");
		TimeUnit.SECONDS.sleep(5);
		task.join();
		System.out.println("Task end");
	}

	@Override
	protected void compute() {
		List<FindDirsFiles> subTasks = new ArrayList<>();

		File[] files = path.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					subTasks.add(new FindDirsFiles(file));
				} else {
					if (file.getAbsolutePath().endsWith(".txt"))
						System.out.println(file.getAbsolutePath());
				}
			}
			if (!subTasks.isEmpty()) {
				for (FindDirsFiles subTask : invokeAll(subTasks)) {
					subTask.join();
				}
			}
		}
	}

}
