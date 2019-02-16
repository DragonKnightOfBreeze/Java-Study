package com.windea.study.struts2.day02.action;

import com.opensymphony.xwork2.ActionSupport;
import com.windea.study.struts2.day02.domain.User;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

/**
 * 使用原始方式得到表单数据
 */
@Namespace("/day02")
public class Form1Action extends ActionSupport {
	@Override
	@Action("form1")
	public String execute() throws Exception {
		//STEP 得到表单数据
		var req = ServletActionContext.getRequest();
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String address = req.getParameter("address");

		//STEP 封装实体类对象
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setAddress(address);

		System.out.println(user);
		return NONE;
	}
}
