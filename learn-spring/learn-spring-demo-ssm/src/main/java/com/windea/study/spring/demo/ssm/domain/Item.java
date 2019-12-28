package com.windea.study.spring.demo.ssm.domain;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Item implements Serializable {
	private static final long serialVersionUID = 3429681834844352742L;

	private Integer id;

	@Size(min = 1, max = 30, message = "{Item.name.Size.error}")
	@NotNull(message = "{Item.name.NotNull.error}")
	private String name;

	@Range(min = 0, message = "{Item.price.Range.error}")
	@NotNull(message = "{Item.price.NotNull.error}")
	private Double price;

	private String detail;

	private String imageUrl;

	@NotNull(message = "{Item.createTime.NotNull.error}")
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

