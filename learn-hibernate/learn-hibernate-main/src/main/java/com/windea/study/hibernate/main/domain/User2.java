package com.windea.study.hibernate.main.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_user2")
public class User2 {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String userName;
	@Column
	private String password;
	//NOTE targetEntity的值也可以不设置，使用默认值
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Role.class)
	@BatchSize(size = 1)
	//NOTE 可以省略，使用默认配置
	@JoinTable(
			//第三张表的名称
			name = "t_user2role",
			//该实体类对应的第三张表的外键的名称
			joinColumns = @JoinColumn(name = "user_id"),
			//另一个实体类对应的第三张表的外键的名称
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roleSet = new HashSet<>();


	public User2() {
	}

	public User2(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}
}
