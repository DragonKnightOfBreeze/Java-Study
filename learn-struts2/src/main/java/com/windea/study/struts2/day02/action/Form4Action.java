package com.windea.study.struts2.day02.action;

import com.opensymphony.xwork2.ActionSupport;
import com.windea.study.struts2.day02.domain.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

/**
 * 使用表达式封装
 */
@Namespace("/day02")
@Action("form4")
public class Form4Action extends ActionSupport {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(user);
		return NONE;
	}
}
