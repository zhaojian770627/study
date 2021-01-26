package com.zj.study.framework.spring.lock.redisson.server;

import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.zj.study.framework.spring.lock.config.VerInfo;
import com.zj.study.framework.spring.lock.redisson.common.ExecutorInterface;

public class ServiceRegisterBean implements InitializingBean, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		RedissonClient redissonClient = applicationContext.getBean(RedissonClient.class);
		VerInfo verInfo = applicationContext.getBean(VerInfo.class);
		RRemoteService remoteService = redissonClient.getRemoteService();
		ExecutorInterfaceImpl impl = new ExecutorInterfaceImpl(verInfo.getServerFlag(), applicationContext);
		remoteService.register(ExecutorInterface.class, impl, 2);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
