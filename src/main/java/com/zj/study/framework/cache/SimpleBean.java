package com.zj.study.framework.cache;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource(objectName = "zj.jmx:type=SimpleBean", description = "缓存测试类")
@Component
public class SimpleBean {

}