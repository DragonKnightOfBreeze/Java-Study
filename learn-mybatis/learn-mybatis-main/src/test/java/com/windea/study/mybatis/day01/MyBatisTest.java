package com.windea.study.mybatis.day01;

import com.windea.study.mybatis.day01.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

class MyBatisTest {
	@Test
	void findUserByIdTest() throws IOException {
		//创建会话工厂
		var inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		var sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到SqlSession
		try(var session = sqlSessionFactory.openSession()) {
			//通过SqlSession操作数据库
			//参数：statement，命名空间加statement的id
			//参数：parameter，匹配parameterType的参数
			//返回resultType类型的对象
			User user = session.selectOne("test.findUserById", 1);
			//输出
			System.out.println(user);
		}
	}

	@Test
	void findUserByUsernameTest() throws IOException {
		var inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		var sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try(var session = sqlSessionFactory.openSession()) {
			List<User> list = session.selectList("test.findUserByUsername", "Windea");
			list.forEach(System.out::println);
		}
	}

	@Test
	void insertUserTest() throws IOException {
		var inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		var sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try(var session = sqlSessionFactory.openSession()) {
			User user = new User();
			user.setUsername("Idea");
			user.setBirthday(new Date());
			user.setSex(1);
			user.setAddress("不知道");
			session.insert("test.insertUser", user);
			//提交事务
			//NOTE 和Spring整合后就不需要了
			session.commit();
			//获取用户信息的主键
			System.out.println(user.getId());
		}
	}

	@Test
	void deleteUserTest() throws IOException {
		var inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		var sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try(var session = sqlSessionFactory.openSession()) {
			session.delete("test.deleteUser", 3);
		}
	}

	@Test
	void updateUserTest() throws IOException {
		var inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
		var sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try(var session = sqlSessionFactory.openSession()) {
			User user = new User();
			user.setId(3);
			user.setUsername("Idea");
			user.setBirthday(new Date());
			user.setSex(1);
			user.setAddress("还是不知道");
			session.update("test.updateUser", user);
			//提交事务
			//NOTE 和Spring整合后就不需要了
			session.commit();
		}
	}
}
