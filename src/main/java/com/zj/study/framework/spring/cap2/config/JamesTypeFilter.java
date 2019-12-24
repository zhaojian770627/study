package com.zj.study.framework.spring.cap2.config;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class JamesTypeFilter implements TypeFilter {
	private ClassMetadata classMetadata;

	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		classMetadata = metadataReader.getClassMetadata();
		Resource resource = metadataReader.getResource();

		String className = classMetadata.getClassName();
		System.out.println("----->" + className);
		if (className.contains("Order")) {
			return true;
		}
		return false;
	}

}
