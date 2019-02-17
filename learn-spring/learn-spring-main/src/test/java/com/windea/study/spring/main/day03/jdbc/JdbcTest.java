package com.windea.study.spring.main.day03.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

class JdbcTest {
	@Test
	void add() {
		//设置数据库信息
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/SSHLesson?serverTimezone=GMT%2B8");
		ds.setUsername("Windea");
		ds.setPassword("BreezesLanding");
		//创建JdbcTemplate对象，设置数据源
		JdbcTemplate template = new JdbcTemplate(ds);

		//调用相关方法完成操作
		String sql = "insert into t_user(userName,password,address) values(?,?,?)";
		int rows = template.update(sql, "123", "123", "123");
		System.out.println(rows);
	}

	@Test
	void queryCount() {
		//设置数据库信息
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/SSHLesson?serverTimezone=GMT%2B8");
		ds.setUsername("Windea");
		ds.setPassword("BreezesLanding");
		//创建JdbcTemplate对象，设置数据源
		JdbcTemplate template = new JdbcTemplate(ds);

		String sql = "select count(*) from t_user";
		//参数：sql语句，返回值类型
		Integer count = template.queryForObject(sql, Integer.class);
		System.out.println(count);
	}

	@Test
	void queryBean() {
		//设置数据库信息
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/SSHLesson?serverTimezone=GMT%2B8");
		ds.setUsername("Windea");
		ds.setPassword("BreezesLanding");
		//创建JdbcTemplate对象，设置数据源
		JdbcTemplate template = new JdbcTemplate(ds);

		String sql = "select * from t_user where userName=?";
		//参数：sql语句，RowMapper（lambda:rs,num->T），多个参数
		var user = template.queryForObject(sql, (rs, num) -> {
			User u = new User();
			u.setUserName(rs.getString("userName"));
			u.setPassword(rs.getString("password"));
			u.setAddress(rs.getString("address"));
			return u;
		}, "Windea");
		System.out.println(user);
	}

	@Test
	void queryBeanList() {
		//设置数据库信息
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/SSHLesson?serverTimezone=GMT%2B8");
		ds.setUsername("Windea");
		ds.setPassword("BreezesLanding");
		//创建JdbcTemplate对象，设置数据源
		JdbcTemplate template = new JdbcTemplate(ds);

		String sql = "select * from t_user";
		//参数：sql语句，RowMapper（lambda:rs,num->T），多个参数
		List<User> list = template.query(sql, (rs, num) -> {
			User u = new User();
			u.setUserName(rs.getString("userName"));
			u.setPassword(rs.getString("password"));
			u.setAddress(rs.getString("address"));
			return u;
		});
		list.forEach(System.out::println);
	}
}
