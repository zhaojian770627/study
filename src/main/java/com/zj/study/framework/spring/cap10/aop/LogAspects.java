package com.zj.study.framework.spring.cap10.aop;

import org.aspectj.lang.JoinPoint;
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

	@Before(value = "pointCut()")
	public void logStart(JoinPoint joinPoint) {
		System.out.println("除法运行。。。。。" + joinPoint.getArgs());
	}

	@After("pointCut()")
	public void logEnd() {
		System.out.println("除法结束。。。。。");
	}

	@AfterReturning(pointcut = "pointCut()", returning = "ret")
	public void logReturn(Object ret) {
		System.out.println("除法正常返回。。。。。return:" + ret);
	}

	@AfterThrowing(throwing = "ex", pointcut = "pointCut()")
	public void logException(Throwable ex) {
		System.out.println("运行异常。。。。。");
		System.out.println(ex.getMessage());
	}

	@Around("pointCut()")
	public Object Around(ProceedingJoinPoint preceedingJoint) throws Throwable {
		System.out.println("@Arount:执行目标方法之前");
		Object ret = preceedingJoint.proceed();
		System.out.println("@Arount:执行目标方法之后");
		return ret;
	}
}
