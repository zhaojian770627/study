package com.zj.study.freemarker.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	/*
	 * 得到相对路径
	 */
	public static String getRelativePath(File baseDir, File file) {
		if (baseDir.equals(file))
			return "";

		if (baseDir.getParentFile() == null)
			return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());

		return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
	}

	public static List<File> searchAllFile(File dir) {
		ArrayList arrayList = new ArrayList();
		searchFies(dir, arrayList);
		return arrayList;

	}

	public static void searchFies(File dir, List<File> collector) {
		if (dir.isDirectory()) {
			File[] subFiles = dir.listFiles();
			for (int i = 0; i < subFiles.length; i++) {
				searchFies(subFiles[i], collector);
			}
		} else {
			collector.add(dir);
		}
	}

	public static File mkdir(String dir, String file) {
		if (dir == null)
			throw new IllegalArgumentException("dir must be not null");
		File result = new File(dir, file);
		if (result.getParentFile() != null) {
			result.getParentFile().mkdir();
		}
		return result;
	}
}
