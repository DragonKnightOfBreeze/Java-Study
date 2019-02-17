package com.windea.study.struts2.main.day03.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/com/windea/study/struts2/main/day03")
@Action("test1")
public class Test1Action extends ActionSupport {
	@Override
	public String execute() throws Exception {
		return NONE;
	}

	String test1() {
		//STEP 得到值栈对象
		var context = ActionContext.getContext();
		var vs = context.getValueStack();
		return NONE;
	}

	String testSet1() {
		//STEP 存放数据 第一种方式
		var context = ActionContext.getContext();
		var vs = context.getValueStack();
		vs.set("userName", "Windea");
		return NONE;
	}

	String testSet2() {
		//STEP 存放数据 第二种方式
		var context = ActionContext.getContext();
		var vs = context.getValueStack();
		vs.push("Windea");
		return NONE;
	}

	//STEP 存放数据 第三种方式
	private String name;

	public String getName() {
		return name;
	}
}
