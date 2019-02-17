package com.windea.study.spring.main.day03.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TxDao {
	//得到JdbcTemplate对象
	private final JdbcTemplate template;

	@Autowired
	public TxDao(JdbcTemplate template) {
		this.template = template;
	}

	public void addMoney(String userName, int money) {
		String sql = "update t_user_tx set money=money+? where userName=?";
		template.update(sql, money, userName);
	}

	public void subMoney(String userName, int money) {
		String sql = "update t_user_tx set money=money-? where userName=?";
		template.update(sql, money, userName);
	}
}
