package com.windea.study.springmvc.main.mapper;

import com.windea.study.springmvc.main.config.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class UserMapperTest {
	private static AnnotationConfigApplicationContext context;

	@BeforeAll
	static void setUp() throws Exception {
		context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class, DatabaseConfig.class, DispatcherConfig.class);
		context.refresh();
	}

	@Test
	void testInsert() throws Exception {
		var mapper = context.getBean(UserMapper.class);
		mapper.insert(null);
	}
}
