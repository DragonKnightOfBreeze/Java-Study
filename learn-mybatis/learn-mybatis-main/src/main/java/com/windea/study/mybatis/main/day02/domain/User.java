package com.windea.study.mybatis.main.day02.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息的实体类
 */
public class User implements Serializable {
	private static final long serialVersionUID = -8934370784739738931L;

	private Integer id;
	private String username;
	private Date birthday;
	private Integer sex;
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
