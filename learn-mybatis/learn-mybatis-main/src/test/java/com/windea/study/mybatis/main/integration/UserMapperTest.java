package com.windea.study.mybatis.main.integration;

import com.windea.study.mybatis.main.integration.config.*;
import com.windea.study.mybatis.main.integration.mapper.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

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

	@Test
	void test1() {
		var map1 = Map.of("k1", "v1", "k2", "v2");
	}
}
