package com.windea.study.struts2.day01.action;

import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		return NONE;
	}

	public String open() throws Exception {
		return "open";
	}

	public String close() throws Exception {
		return "close";
	}
}
