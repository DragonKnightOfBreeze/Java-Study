package com.windea.study.mybatis.day01;

import com.windea.study.mybatis.day01.dao.UserDao;
import com.windea.study.mybatis.day01.dao.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserDaoTest {
	private static SqlSessionFactory sqlSessionFactory;

	@BeforeAll
	static void setUp() throws Exception {
		//创建会话工厂
		var inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	void findUserByIdTest() throws Exception {
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		var user = userDao.findUserById(4);
		System.out.println(user);
	}
}
