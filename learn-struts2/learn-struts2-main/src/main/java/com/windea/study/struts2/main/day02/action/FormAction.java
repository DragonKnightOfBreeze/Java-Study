package com.windea.study.struts2.main.day02.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

@Namespace("/day02")
public class FormAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	@Action("form")
	public String execute() throws Exception {
		test3();
		return NONE;
	}

	void test1() {
		//STEP 第一种方式 使用ActionContext类得到表单参数
		var context = ActionContext.getContext();
		var params = context.getParameters();
		var userName = params.get("userName");
		var password = params.get("password");
		var address = params.get("address");
		System.out.println(userName + "\t" + password + "\t" + address);
	}

	void test2() {
		//STEP 第二种方式 使用ServletActionContext类获取
		var req = ServletActionContext.getRequest();
		var userName = req.getParameter("userName");
		var password = req.getParameter("password");
		var address = req.getParameter("address");
		System.out.println(userName + "\t" + password + "\t" + address);
	}

	void test3() {
		//STEP 第三种方式 使用接口注入
		var req = request;
		var userName = req.getParameter("userName");
		var password = req.getParameter("password");
		var address = req.getParameter("address");
		System.out.println(userName + "\t" + password + "\t" + address);
	}

	void test4() {
		//STEP 操作域对象
		var req = ServletActionContext.getRequest();
		var session = req.getSession();
		var application = req.getServletContext();
	}
}
