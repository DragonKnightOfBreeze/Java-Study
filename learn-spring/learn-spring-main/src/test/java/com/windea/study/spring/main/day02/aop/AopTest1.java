package com.windea.study.spring.main.day02.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class AopTest1 {
	@Test
	void testAop() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-config-day02-aop.xml");
		AopBook1 book = (AopBook1) context.getBean("aopBook1");
		book.print();
	}
}
