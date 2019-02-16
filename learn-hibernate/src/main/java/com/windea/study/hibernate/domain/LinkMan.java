package com.windea.study.hibernate.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_linkMan")
public class LinkMan {
	@Column @Id @GeneratedValue
	private Integer id;
	@Column
	private String name;
	@Column
	private String gender;
	@Column
	private String phone;
	@ManyToOne
	//NOTE 定义外键，可以省略，都没有则使用连接表
	@JoinColumn(name = "client_id")
	private Client client;

	public LinkMan() {
	}

	public LinkMan(String name, String gender, String phone) {
		this.name = name;
		this.gender = gender;
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
