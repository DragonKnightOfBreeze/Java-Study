package com.windea.study.springmvc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windea.study.springmvc.domain.Item;
import com.windea.study.springmvc.domain.ItemQueryVo;
import com.windea.study.springmvc.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	private final ItemMapper itemMapper;

	@Autowired
	public ItemServiceImpl(ItemMapper itemMapper) {this.itemMapper = itemMapper;}


	@Override
	public void insert(Item item) throws Exception {
		if(item == null)
			throw new RuntimeException();
		itemMapper.insert(item);
	}

	@Override
	public int deleteById(Integer id) {
		return itemMapper.deleteById(id);
	}

	@Override
	public Item findById(Integer id) throws Exception {
		if(id == null)
			throw new RuntimeException();
		return itemMapper.findById(id);
	}

	@Override
	public List<Item> findAll() throws Exception {
		return itemMapper.findAll();
	}

	@Override
	public List<Item> findByConditions(ItemQueryVo queryVo) {
		return itemMapper.findByConditions(queryVo);
	}

	@Override
	public int updateById(Integer id, Item item) {
		return itemMapper.updateById(id, item);
	}

	@Override
	public PageInfo<Item> findAllWithPage(int page, int pageSize) throws Exception {
		//lambda不抛出异常
		//return PageHelper.startPage(page, pageSize).doSelectPage(itemMapper::findAll);
		PageHelper.startPage(page, pageSize);
		return new PageInfo<>(itemMapper.findAll());
	}
}
