package com.windea.study.mybatis.main.day02;

import com.windea.study.mybatis.main.day02.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserMapperTest {
	private static SqlSessionFactory sqlSessionFactory;

	@BeforeAll
	static void setUp() throws Exception {
		//创建会话工厂
		var inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	void findOrderWithUserTest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		var mapper = sqlSession.getMapper(UserMapper.class);
		var list = mapper.findUserInDetail();
		System.out.println(list);
	}
}
