package com.windea.study.mybatis.integration;

import com.windea.study.mybatis.integration.config.*;
import com.windea.study.mybatis.integration.mapper.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class UserMapperTest {
	private static AnnotationConfigApplicationContext context;

	@BeforeAll
	static void setUp() throws Exception {
		context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class, DatabaseConfig.class, MapperConfig.class);
		context.refresh();
	}

	@Test
	void findUserById() throws Exception {
		UserMapper mapper = (UserMapper) context.getBean("userMapper");
		var user = mapper.findUserById(5);
		System.out.println(user);
	}
}
