package com.windea.study.springmvc.main.domain;

import java.io.Serializable;
import java.util.List;

public class ItemQueryVo implements Serializable {
	private static final long serialVersionUID = -8778918527618074189L;

	private ItemEx item;

	private List<Item> itemList;

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
}
