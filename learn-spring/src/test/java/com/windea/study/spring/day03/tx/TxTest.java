package com.windea.study.spring.day03.tx;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class TxTest {
	@Test
	void testService() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Config.class);
		context.refresh();
		var service = context.getBean(TxService.class);
		service.transfer();
	}
}
