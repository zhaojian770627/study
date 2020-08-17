package com.zj.study.framework.tcc.one;

import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "zj.jmx:type=FacadeBean", description = "外观测试类")
public class FacadeBean {
}