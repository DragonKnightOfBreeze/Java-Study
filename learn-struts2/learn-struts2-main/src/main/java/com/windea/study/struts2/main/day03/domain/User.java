package com.windea.study.struts2.main.day03.domain;

import java.io.Serializable;

public class User implements Serializable {
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
	public String toString() {
		return "User[userName:" + userName + "][password:" + password + "][address:" + address + "]";
	}
}
