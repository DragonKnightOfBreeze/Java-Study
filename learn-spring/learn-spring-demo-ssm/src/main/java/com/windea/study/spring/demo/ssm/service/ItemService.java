package com.windea.study.spring.demo.ssm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.windea.study.spring.demo.ssm.domain.Item;
import com.windea.study.spring.demo.ssm.domain.ItemQueryVo;
import com.windea.study.spring.demo.ssm.mapper.IItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService implements IItemService {
	private final IItemMapper itemMapper;

	@Autowired
	public ItemService(IItemMapper itemMapper) {this.itemMapper = itemMapper;}


	@Override
	public void insert(Item item) {
		if(item == null)
			throw new RuntimeException();
		itemMapper.insert(item);
	}

	@Override
	public void deleteById(Integer id) {
		itemMapper.deleteById(id);
	}

	@Override
	public Item findById(Integer id) {
		if(id == null)
			throw new RuntimeException();
		return itemMapper.findById(id);
	}

	@Override
	public List<Item> findAll() {
		return itemMapper.findAll();
	}

	@Override
	public List<Item> findByConditions(ItemQueryVo queryVo) {
		return itemMapper.findByConditions(queryVo);
	}

	@Override
	public void updateById(Integer id, Item item) {
		itemMapper.updateById(id, item);
	}

	@Override
	public Page<Item> findAllWithPage(int page, int pageSize) {
		return PageHelper.startPage(page, pageSize).doSelectPage(itemMapper::findAll);
	}
}
