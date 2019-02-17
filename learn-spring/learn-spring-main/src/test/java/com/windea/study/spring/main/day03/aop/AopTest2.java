package com.windea.study.spring.main.day03.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AopTest2 {
	@Test
	void testAop() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AopConfig.class);
		context.refresh();
		AopService service = context.getBean(AopService.class);
		service.print();
	}
}
