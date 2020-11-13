package com.zj.study.freemarker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
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
		StringWriter w = new StringWriter();
		template.process(dataModel, w);
		System.out.println(w.toString());
	}

}
