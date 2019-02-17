/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.study.spring.demo.ssh.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.windea.study.spring.demo.ssh.domain.User;
import com.windea.study.spring.demo.ssh.service.UserService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 用户的action
 * <br>需要有空的构造方法。
 */
//表示这个类被Spring托管，且在控制层
@Controller
//表示这个类是多实例的
@Scope("prototype")
//action的命名空间
@Namespace("/")
//Action的默认配置
@Action("user")
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private final UserService service;
	//需要实例化
	private User user = new User();

	@Autowired
	public UserAction(UserService service) {
		this.service = service;
	}

	/**
	 * 封装表单信息到指定的实体类。
	 */
	@Override
	public User getModel() {
		return user;
	}

	/**
	 * 默认执行的方法。
	 */
	@Override
	public String execute() throws Exception {
		System.out.println("Hello world!");
		return NONE;
	}

	/**
	 * 添加用户。
	 */
	@Action(value = "addUser", results = @Result(name = SUCCESS, location = "/index.jsp"))
	public String add() throws Exception {
		service.add(user);
		return SUCCESS;
	}
}
