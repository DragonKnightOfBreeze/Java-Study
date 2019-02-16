package com.windea.study.hibernate.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private String description;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = User2.class, mappedBy = "roleSet")
	private Set<User2> userSet = new HashSet<>();


	public Role() {
	}

	public Role(String name, String description) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User2> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User2> userSet) {
		this.userSet = userSet;
	}
}
