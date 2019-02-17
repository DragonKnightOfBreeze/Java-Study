package com.windea.study.struts2.main.day02.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.windea.study.struts2.main.day02.domain.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;


/**
 * 使用模型驱动得到表单数据
 */
@Namespace("/day02")
@Action("form3")
public class Form3Action extends ActionSupport implements ModelDriven<User> {
	//STEP 创建对象（要求name顺序值与对象属性一一对应）
	private User user = new User();

	//STEP 实现方法
	@Override
	public User getModel() {
		return user;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(user);
		return NONE;
	}
}
