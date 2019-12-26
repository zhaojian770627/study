package com.zj.study.framework.spring.cap6.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class JamesImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// 返回全类名的bean
		return new String[] { "com.zj.study.framework.spring.cap6.bean.Fish",
				"com.zj.study.framework.spring.cap6.bean.Tiger" };
	}
}
