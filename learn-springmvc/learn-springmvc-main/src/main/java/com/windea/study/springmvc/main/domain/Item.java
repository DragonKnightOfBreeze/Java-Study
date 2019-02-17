package com.windea.study.springmvc.main.domain;

import com.windea.study.springmvc.main.validation.ValidationGroup1;
import com.windea.utility.base.ext.ObjectExt;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Item implements Serializable {
	private static final long serialVersionUID = 3429681834844352742L;

	private Integer id;

	@Size(min = 1, max = 30, message = "{Item.name.Size.error}", groups = ValidationGroup1.class)
	private String name;

	private Double price;

	private String detail;

	private String imageUrl;

	@NotNull(message = "{Item.name.NotNull.error}")
	private LocalDateTime createTime;


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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
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

