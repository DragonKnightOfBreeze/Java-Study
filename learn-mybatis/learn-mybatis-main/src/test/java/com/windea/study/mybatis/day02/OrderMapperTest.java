package com.windea.study.mybatis.day02;

import com.windea.study.mybatis.day02.mapper.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OrderMapperTest {
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
		var mapper = sqlSession.getMapper(OrderMapper.class);
		var list = mapper.findOrderWithUser();
		System.out.println(list);
	}

	@Test
	void findOrderWithUserTest2() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		var mapper = sqlSession.getMapper(OrderMapper.class);
		var list = mapper.findOrderWithUser2();
		list.forEach(e -> System.out.println(e));
	}

	@Test
	void findOrderInDetailTest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		var mapper = sqlSession.getMapper(OrderMapper.class);
		var list = mapper.findOrderInDetail();
		list.forEach(e -> System.out.println(e));
	}
}

