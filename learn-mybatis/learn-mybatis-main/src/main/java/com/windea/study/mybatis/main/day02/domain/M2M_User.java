package com.windea.study.mybatis.main.day02.domain;

import com.windea.utility.base.ext.ObjectExt;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息的实体类
 */
public class M2M_User implements Serializable {
	private static final long serialVersionUID = -1939285093847253854L;

	private Integer id;
	private String username;
	private Date birthday;
	private Integer sex;
	private String address;

	private List<M2M_Order> orderList;


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

	public List<M2M_Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<M2M_Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public boolean equals(Object obj) {
		return ObjectExt.objEquals(this, obj);
	}

	@Override
	public String toString() {
		return ObjectExt.objToString(this);
	}

	@Override
	public int hashCode() {
		return ObjectExt.objHashcode(this);
	}
}

