package com.windea.study.spring.demo.ssm.service;

import com.github.pagehelper.Page;
import com.windea.study.spring.demo.ssm.domain.Item;
import com.windea.study.spring.demo.ssm.domain.ItemQueryVo;

import java.util.List;

public interface IItemService {
	void insert(Item item);

	void deleteById(Integer id);

	Item findById(Integer id);

	List<Item> findAll();

	List<Item> findByConditions(ItemQueryVo queryVo);

	void updateById(Integer id, Item item);

	Page<Item> findAllWithPage(int page, int pageSize);
}
