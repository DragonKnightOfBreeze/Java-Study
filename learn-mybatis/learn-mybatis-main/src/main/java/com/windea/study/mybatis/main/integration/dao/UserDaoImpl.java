package com.windea.study.mybatis.main.integration.dao;

import com.windea.study.mybatis.main.integration.domain.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public User findUserById(int id) throws Exception {
		var sqlSession = getSqlSession();
		return sqlSession.selectOne("test2.findUserById", id);
	}
}
