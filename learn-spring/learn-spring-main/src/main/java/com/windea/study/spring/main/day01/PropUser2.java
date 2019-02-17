package com.windea.study.spring.main.day01;


public class PropUser2 {
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private void print() {
		System.out.println(getUserName());
	}
}

