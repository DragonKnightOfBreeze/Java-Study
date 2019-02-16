package com.windea.study.spring.day01;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class IOCTest {
	@Test
	void testUser1() {
		//加载spring配置文件，创建对象
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-config-day01.xml");
		User1 user = (User1) context.getBean("user");
		user.add();
	}


	@Test
	void testUser2() {
		//加载spring配置文件，创建对象
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-config-day01.xml");
		User1 user = (User1) context.getBean("user2");
		user.add();
	}

	@Test
	void testUser3() {
		//加载spring配置文件，创建对象
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-config-day01.xml");
		User1 user = (User1) context.getBean("user3");
		user.add();
	}

	@Test
	void testUserService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-config-day01.xml");
		UserService1 userService = (UserService1) context.getBean("userService");
		userService.print();
	}


}
