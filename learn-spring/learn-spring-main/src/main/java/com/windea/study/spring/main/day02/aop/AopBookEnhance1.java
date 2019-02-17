package com.windea.study.spring.main.day02.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class AopBookEnhance1 {
	//前置通知
	public void before() {
		System.out.println("open book...");
	}

	//后置通知
	public void after() {
		System.out.println("close book...");
	}

	//环绕通知
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("open book...");
		//执行被增强的方法
		joinPoint.proceed();
		System.out.println("close book...");
	}


}
