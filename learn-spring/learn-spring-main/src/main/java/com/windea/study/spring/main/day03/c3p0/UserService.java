package com.windea.study.spring.main.day03.c3p0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserDao dao;

	@Autowired
	public UserService(UserDao dao) {
		this.dao = dao;
	}

	public void add() {
		dao.add();
	}
}
