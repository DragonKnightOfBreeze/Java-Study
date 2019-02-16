package com.windea.study.struts2.day03.action;

import com.opensymphony.xwork2.ActionSupport;
import com.windea.study.struts2.day03.domain.User;
import org.apache.struts2.convention.annotation.*;

@Namespace("/com/windea/study/struts2/day03")
@Action(value = "test2", results = @Result(name = "ognlForm2", location =
		"/com/windea/study/struts2/day03/ognlTest2.jsp"))
public class Test2Action extends ActionSupport {
	private String userName;

	private User user = new User();

	public String getUserName() {
		return userName;
	}

	public User getUser() {
		return user;
	}


	@Override
	public String execute() throws Exception {
		userName = "Windea";

		getUser().setUserName("Windea");

		return "ognlTest2";
	}


}
