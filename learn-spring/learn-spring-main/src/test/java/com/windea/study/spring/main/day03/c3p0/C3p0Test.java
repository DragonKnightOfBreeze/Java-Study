package com.windea.study.spring.main.day03.c3p0;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class C3p0Test {
	@Test
	void testService() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Config.class);
		context.refresh();
		var service = context.getBean(UserService.class);
		service.add();
	}
}
