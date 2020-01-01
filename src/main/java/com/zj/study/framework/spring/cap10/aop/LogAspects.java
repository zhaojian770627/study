package com.zj.study.framework.spring.cap10.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/*
 * 日志切面类
 */
@Aspect
public class LogAspects {
	@Pointcut("execution(public int com.zj.study.framework.spring.cap10.aop.Calculator.*(..))")
	public void pointCut() {
	}

	@Before("pointCut()")
	public void logStart() {
		System.out.println("除法运行。。。。。");
	}

	@After("pointCut()")
	public void logEnd() {
		System.out.println("除法结束。。。。。");
	}

	@AfterReturning("pointCut()")
	public void logReturn() {
		System.out.println("除法正常返回。。。。。");
	}

	@AfterThrowing("pointCut()")
	public void logException() {
		System.out.println("运行异常。。。。。");
	}

	@Around("pointCut()")
	public Object Around(ProceedingJoinPoint preceedingJoint) throws Throwable {
		System.out.println("@Arount:执行目标方法之前");
		Object ret = preceedingJoint.proceed();
		System.out.println("@Arount:执行目标方法之后");
		return ret;
	}
}
