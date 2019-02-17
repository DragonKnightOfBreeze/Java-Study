package com.windea.study.spring.main.day03.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAspect {
	//前置通知
	@Before("execution(* com.windea.study.spring.main.day03.aop.AopService.*(..))")
	public void before() {
		System.out.println("open book...");
	}

	//后置通知
	@After("execution(* com.windea.study.spring.main.day03.aop.AopService.*(..))")
	public void after() {
		System.out.println("close book...");
	}

	//环绕通知
	@Around("execution(* com.windea.study.spring.main.day03.aop.AopService.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("open book...");
		//执行被增强的方法
		var obj = joinPoint.proceed();
		System.out.println("close book...");
		return obj;
	}
}
