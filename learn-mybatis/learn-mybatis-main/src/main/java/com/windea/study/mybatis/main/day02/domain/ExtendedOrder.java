package com.windea.study.mybatis.main.day02.domain;

/**
 * 订单信息的拓展实体类
 * <br>通过此类映射订单和用户查询的结果。
 */
public class ExtendedOrder extends Order {
	private static final long serialVersionUID = -6177587842298500342L;

	private String username;
	private Integer sex;
	private String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
