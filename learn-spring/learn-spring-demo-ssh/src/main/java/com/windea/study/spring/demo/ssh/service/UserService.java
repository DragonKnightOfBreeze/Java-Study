/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.study.spring.demo.ssh.service;

import com.windea.study.spring.demo.ssh.dao.IUserDao;
import com.windea.study.spring.demo.ssh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户的service类
 */
//表示这个类被Spring托管，且在业务层
@Service
//启用事务
@Transactional
public class UserService {
	private final IUserDao dao;

	@Autowired
	public UserService(IUserDao dao) {
		this.dao = dao;
	}

	public void add(User user) {
		dao.add(user);
	}
}
