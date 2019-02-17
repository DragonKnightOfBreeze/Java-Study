package com.windea.study.spring.main.day01;

public class UserService1 {
	private UserDao1 dao;

	public void setDao(UserDao1 dao) { this.dao = dao; }

	public void print() {
		dao.print();
	}
}
