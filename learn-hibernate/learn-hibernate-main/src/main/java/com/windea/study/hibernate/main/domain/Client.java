package com.windea.study.hibernate.main.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_client")
public class Client {
	@Column @Id @GeneratedValue
	private Integer id;
	@Column
	private String name;
	@Column
	private String level;
	@Column
	private String phone;
	//mappedBy：要映射到的另外的实体类的属性的名称
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private Set<LinkMan> linkManSet = new HashSet<>();

	public Client() {
	}

	public Client(String name, String level, String phone) {
		this.name = name;
		this.level = level;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<LinkMan> getLinkManSet() {
		return linkManSet;
	}

	public void setLinkManSet(Set<LinkMan> linkManSet) {
		this.linkManSet = linkManSet;
	}
}
