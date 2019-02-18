package com.windea.study.mybatis.main.day02.domain;

import com.windea.utility.base.ext.ObjectExt;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单信息的实体类
 */
public class Order implements Serializable {
	private static final long serialVersionUID = -7642425824094803593L;

	private Integer id;

	private Integer userId;

	private String number;

	private LocalDateTime createTime;

	private String note;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

