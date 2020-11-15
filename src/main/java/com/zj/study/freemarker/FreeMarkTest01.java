package com.zj.study.freemarker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkTest01 {

	public static void main(String[] args) throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		ClassTemplateLoader ftl = new ClassTemplateLoader(FreeMarkTest01.class, ".");
		cfg.setTemplateLoader(ftl);
		Template template = cfg.getTemplate("t1.ftl");
		Map<String, Object> dataModel = new HashMap<>();
		dataModel.put("username", "freemark");
		dataModel.put("flag", 2);
		List<String> weeks = new ArrayList<>();
		weeks.add("星期一");
		weeks.add("星期二");
		weeks.add("星期三");
		weeks.add("星期四");
		weeks.add("星期五");
		weeks.add("星期六");
		weeks.add("星期日");
		dataModel.put("weeks", weeks);
		template.process(dataModel, new PrintWriter(System.out));
		System.out.println("ok");
	}

}
