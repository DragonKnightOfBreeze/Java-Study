package com.windea.study.spring.day02.bean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService2")
public class UserService2 {
	////创建dao字段，使用注解方式时，不需要set方法
	////使用@Autowired注解（不推荐）
	//@Autowired
	//private UserDao dao;

	//使用@Resource注解
	@Resource(name = "userDao2")
	private UserDao2 dao;

	public void print() {
		dao.print();
	}
}
