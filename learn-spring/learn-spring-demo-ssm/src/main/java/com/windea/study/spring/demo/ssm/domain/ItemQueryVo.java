package com.windea.study.spring.demo.ssm.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ItemQueryVo implements Serializable {
	private static final long serialVersionUID = -8778918527618074189L;

	private ItemEx item;

	private List<Item> itemList;

	private Double minPrice;

	private Double maxPrice;

	private LocalDateTime createFrom;

	public ItemEx getItem() {
		return item;
	}

	public void setItem(ItemEx item) {
		this.item = item;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public LocalDateTime getCreateFrom() {
		return createFrom;
	}

	public void setCreateFrom(LocalDateTime createFrom) {
		this.createFrom = createFrom;
	}
}
