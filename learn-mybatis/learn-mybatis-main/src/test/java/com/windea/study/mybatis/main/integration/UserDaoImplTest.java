package com.windea.study.mybatis.main.integration;

import com.windea.study.mybatis.main.integration.config.AppConfig;
import com.windea.study.mybatis.main.integration.config.DatabaseConfig;
import com.windea.study.mybatis.main.integration.dao.UserDaoImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class UserDaoImplTest {
	private static AnnotationConfigApplicationContext context;

	@BeforeAll
	static void setUp() throws Exception {
		context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class, DatabaseConfig.class);
		context.refresh();
	}

	@Test
	void findUserById() throws Exception {
		var dao = context.getBean(UserDaoImpl.class);
		var user = dao.findUserById(5);
		System.out.println(user);
	}
}
