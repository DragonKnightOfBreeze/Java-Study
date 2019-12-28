package com.windea.study.mybatis.main.day02.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品信息的实体类
 */
public class Item implements Serializable {
	private static final long serialVersionUID = -640238108522383676L;

	private Integer id;

	private String name;

	private Double price;

	private String detail;

	private String imageUrl;

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
}

