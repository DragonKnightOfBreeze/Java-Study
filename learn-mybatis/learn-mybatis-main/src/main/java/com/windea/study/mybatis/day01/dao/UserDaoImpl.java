package com.windea.study.mybatis.day01.dao;

import com.windea.study.mybatis.day01.domain.User;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {
	private SqlSessionFactory sessionFactory;

	public UserDaoImpl(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User findUserById(int id) throws Exception {
		try(var sqlSession = sessionFactory.openSession()) {
			return sqlSession.selectOne("test.findUserById", id);
		}
	}

	@Override
	public List<User> findUserByUsername(String username) throws Exception {
		try(var sqlSession = sessionFactory.openSession()) {
			return sqlSession.selectList("test.findUserByUsername", username);
		}
	}

	@Override
	public List<User> findAllUser() throws Exception {
		try(var sqlSession = sessionFactory.openSession()) {
			return sqlSession.selectList("test.findAllUser");
		}
	}

	@Override
	public void insertUser(User user) throws Exception {
		try(var sqlSession = sessionFactory.openSession()) {
			sqlSession.insert("test.insertUser", user);
			sqlSession.commit();
		}
	}

	@Override
	public void deleteUserById(int id) throws Exception {
		try(var sqlSession = sessionFactory.openSession()) {
			sqlSession.delete("test.deleteUserById", id);
			sqlSession.commit();
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		try(var sqlSession = sessionFactory.openSession()) {
			sqlSession.selectList("test.updateUser", user);
			sqlSession.commit();
		}
	}
}
