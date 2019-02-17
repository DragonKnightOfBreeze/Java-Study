package com.windea.study.struts2.main.day01.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.*;

//配置包名
@ParentPackage("struts-default")
//配置命名空间
@Namespace("/day01")
@ExceptionMappings(@ExceptionMapping(result = "error", exception = "java.lang.RuntimeException"))
public class MagicBookAction extends ActionSupport {
	@Override
	public String execute() {
		return NONE;
	}

	//配置结果映射
	@Action(value = "openMagicBook", results = @Result(name = "open", location = "/day01/open.jsp"))
	public String open() {
		return "open";
	}

	@Action(value = "closeMagicBook", results = @Result(name = "close", location =
			"/day01/close.jsp"))
	public String close() {
		return "close";
	}
}

