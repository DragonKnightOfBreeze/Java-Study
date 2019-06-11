package com.windea.study.spring.demo.ssm.mapper;

import com.windea.study.spring.demo.ssm.domain.Item;
import com.windea.study.spring.demo.ssm.domain.ItemQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IItemMapper {
	void insert(@Param("item") Item item);

	void updateById(@Param("id") Integer id, @Param("item") Item item);

	void deleteById(@Param("id") Integer id);

	Item findById(@Param("id") Integer id);

	List<Item> findAll();

	List<Item> findByConditions(ItemQueryVo queryVo);
}
