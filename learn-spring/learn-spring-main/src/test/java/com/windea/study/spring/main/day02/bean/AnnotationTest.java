package com.windea.study.spring.main.day02.bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class AnnotationTest {
	@Test
	void testUser() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-config-day02.xml");
		User user = (User) context.getBean("user");
		user.print();
	}

	@Test
	void testService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-config-day02.xml");
		UserService2 service = (UserService2) context.getBean("userService");
		service.print();
	}
}
