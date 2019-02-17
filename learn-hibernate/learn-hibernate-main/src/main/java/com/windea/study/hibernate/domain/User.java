package com.windea.study.hibernate.domain;

import javax.persistence.*;

/**
 * 测试用实体类（使用注解的方式持久化）
 */
@Entity
@Table(name = "t_user")
public class User {
	@Column @Id @GeneratedValue
	//	@Column @Id @GeneratedValue(generator="system-uuid")
	//	@GenericGenerator(name="system-uuid",strategy = "uuid")
	private int id;
	@Column
	private String userName;
	@Column
	private String password;
	@Column
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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


}
