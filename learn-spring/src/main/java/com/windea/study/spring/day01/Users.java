package com.windea.study.spring.day01;

import java.util.*;

public class Users {
	private List<String> userList;
	private Map<String, String> userMap;
	private Properties userProps;


	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	public void setUserMap(Map<String, String> userMap) {
		this.userMap = userMap;
	}

	public void setUserProps(Properties userProps) {
		this.userProps = userProps;
	}

	public void print() {
		userList.forEach(System.out::println);
		userMap.forEach((k, v) -> System.out.println(k + "\n" + v));
		userProps.forEach((k, v) -> System.out.println(k + "\n" + v));
	}
}
