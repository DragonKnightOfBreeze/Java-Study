package com.windea.study.mybatis.main.day02.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class M2M_Order implements Serializable {
	private static final long serialVersionUID = 1695474901869238283L;

	private Integer id;
	private String number;
	private LocalDateTime createTime;
	private String note;

	private List<M2M_OrderDetail> orderDetailList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<M2M_OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<M2M_OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
}
