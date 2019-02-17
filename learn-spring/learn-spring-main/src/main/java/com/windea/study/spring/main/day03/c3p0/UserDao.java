package com.windea.study.spring.main.day03.c3p0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	//得到JdbcTemplate对象
	private final JdbcTemplate template;

	@Autowired
	public UserDao(JdbcTemplate template) {
		this.template = template;
	}

	public void add() {
		String sql = "insert into t_user(userName,password,address) values(?,?,?)";
		template.update(sql, "233", "233", "123");
	}
}
