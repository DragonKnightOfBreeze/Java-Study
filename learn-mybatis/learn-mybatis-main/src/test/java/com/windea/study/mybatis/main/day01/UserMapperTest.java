package com.windea.study.mybatis.main.day01;

import com.windea.study.mybatis.main.day01.mapper.UserMapper;
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
	void findUserByIdTest() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//自动生成mapper代理对象
		//NOTE 这里实例化的是一个代理对象，不是借口
		var userMapper = sqlSession.getMapper(UserMapper.class);
		var user = userMapper.findUserById(4);
		System.out.println(user);
	}
}

