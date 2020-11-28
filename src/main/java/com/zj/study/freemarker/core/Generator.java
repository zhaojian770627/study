package com.zj.study.freemarker.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.study.freemarker.utils.FileUtils;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 代码生成器的核心处理类 使用Freemarker完成文件生成 数据模型+模板 数据: 数据模型 模板位置 生成文件的路径
 * 
 * @author zhaojian
 *
 */
public class Generator {
	private String templatePath;// 模板路径
	private String outPath;// 代码生成路径
	private Configuration cfg;

	public Generator(String templatePath, String outPath) throws IOException {
		this.templatePath = templatePath;
		this.outPath = outPath;
		cfg = new Configuration();
		FileTemplateLoader ftl = new FileTemplateLoader(new File(templatePath));
		cfg.setTemplateLoader(ftl);
	}

	public void scanAndGenerator(Map<String, Object> dataModel) throws Exception {
		List<File> fileList = FileUtils.searchAllFile(new File(templatePath));
		for (File file : fileList) {
			executeGenertor(dataModel, file);
		}
	}

	private void executeGenertor(Map<String, Object> dataModel, File file) throws Exception {
		String templateFileName = file.getAbsolutePath().replace(this.templatePath, "");
		String outFileName = processTemplateString(templateFileName, dataModel);

		Template template = cfg.getTemplate(templateFileName);
		template.setOutputEncoding("utf-9");

		File file1 = FileUtils.mkdir(outPath, outFileName);

		FileWriter fw = new FileWriter(file1);
		template.process(dataModel, fw);
		fw.close();
	}

	private String processTemplateString(String templateString, Map<String, Object> dataModel) throws Exception {
		StringWriter out = new StringWriter();
		Template template = new Template("ts", new StringReader(templateString), cfg);
		template.process(dataModel, out);
		return out.toString();
	}

	public static void main(String[] args) throws Exception {
		String templatePath = "D:\\tmp\\template";
		String outPath = "D:\\tmp\\out";

		Generator generator = new Generator(templatePath, outPath);
		Map<String, Object> dataModel = new HashMap<>();
		dataModel.put("username", "zj");
		generator.scanAndGenerator(dataModel);
	}
}
