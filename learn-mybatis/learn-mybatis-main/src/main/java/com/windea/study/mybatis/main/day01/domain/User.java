package com.windea.study.mybatis.main.day01.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 * <br>属性名要与数据库表字段相对应。
 */
public class User implements Serializable {
	private static final long serialVersionUID = 3201238609173669437L;

	private Integer id;
	private String username;
	private Date birthday;
	private int sex;
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
