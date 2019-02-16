package com.windea.study.springmvc.service;

import com.github.pagehelper.PageInfo;
import com.windea.study.springmvc.domain.Item;
import com.windea.study.springmvc.domain.ItemQueryVo;

import java.util.List;

public interface ItemService {
	void insert(Item item) throws Exception;

	int deleteById(Integer id);

	Item findById(Integer id) throws Exception;

	List<Item> findAll() throws Exception;

	List<Item> findByConditions(ItemQueryVo queryVo);

	int updateById(Integer id, Item item);

	PageInfo<Item> findAllWithPage(int page, int pageSize) throws Exception;
}
