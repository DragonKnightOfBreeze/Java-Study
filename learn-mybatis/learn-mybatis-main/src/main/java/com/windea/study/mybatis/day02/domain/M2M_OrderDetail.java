package com.windea.study.mybatis.day02.domain;

import com.windea.utility.base.ext.ObjectExt;

import java.io.Serializable;

public class M2M_OrderDetail implements Serializable {
	private static final long serialVersionUID = -1508909088084325336L;

	private Integer id;
	private Integer itemCount = 0;

	private M2M_Item item;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public M2M_Item getItem() {
		return item;
	}

	public void setItem(M2M_Item item) {
		this.item = item;
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
