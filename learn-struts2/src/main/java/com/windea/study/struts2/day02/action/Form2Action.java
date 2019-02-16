package com.windea.study.struts2.day02.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;


/**
 * 使用属性封装得到表单数据
 */
@Namespace("/day02")
@Action("form2")
public class Form2Action extends ActionSupport {
	//STEP 定义同名字段（与表单输入项的name属性一致）
	private String userName;
	private String password;
	private String address;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	//	@Action("form2")
	public String execute() throws Exception {
		System.out.println("userName:" + userName);
		System.out.println("password:" + password);
		System.out.println("address:" + address);
		return NONE;
	}
}

