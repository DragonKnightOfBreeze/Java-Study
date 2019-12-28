package com.windea.study.mybatis.main.day02.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单信息的实体类
 */
public class O2M_Order implements Serializable {
	private static final long serialVersionUID = -8201224059459177888L;

	private Integer id;
	private String number;
	private LocalDateTime createTime;
	private String note;

	private User user;
	private List<OrderDetail> orderDetailList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
}



