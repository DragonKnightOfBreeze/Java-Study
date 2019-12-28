package com.windea.study.mybatis.main.day02.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单信息的实体类
 */
public class O2O_Order implements Serializable {
	private static final long serialVersionUID = -5638096322203872528L;

	private Integer id;
	private String number;
	private LocalDateTime createTime;
	private String note;
	private Integer userId;

	private User user;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}


