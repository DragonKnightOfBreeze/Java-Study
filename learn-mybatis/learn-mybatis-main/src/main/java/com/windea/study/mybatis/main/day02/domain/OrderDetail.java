package com.windea.study.mybatis.main.day02.domain;

import com.windea.utility.base.ext.ObjectExt;

import java.io.Serializable;

/**
 * 订单明细的实体类
 */
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1706610386215571010L;

	private Integer id;
	private Integer orderId;
	private Integer itemId;
	private Integer itemCount = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
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

