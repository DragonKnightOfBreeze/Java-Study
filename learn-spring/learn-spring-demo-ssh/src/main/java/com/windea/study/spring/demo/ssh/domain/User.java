/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.study.spring.demo.ssh.domain;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户的实体类
 * <br>需要有空的构造方法。
 */
//表示这是一个实体类
@Entity
//表示这是一个数据库表，默认表名同类名
@Table
public class User implements Serializable {
	private static final long serialVersionUID = -472013952935829575L;

	//表示这是一个主键，最好声明生成策略
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//表示这是一个数据库表的字段，默认字段名同属性名
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String address;

	public User() {}

	public User(String username, String password, String address) {
		this.username = username;
		this.password = password;
		this.address = address;
	}

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
		return new ToStringCreator(this).toString();
	}
}
