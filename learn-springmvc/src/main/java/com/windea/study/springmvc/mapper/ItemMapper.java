package com.windea.study.springmvc.mapper;

import com.windea.study.springmvc.domain.Item;
import com.windea.study.springmvc.domain.ItemQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
	void insert(@Param("item") Item item) throws Exception;

	int updateById(@Param("id") Integer id, @Param("item") Item item);

	int deleteById(@Param("id") Integer id);

	Item findById(@Param("id") Integer id) throws Exception;

	List<Item> findAll() throws Exception;

	List<Item> findByConditions(@Param("queryVo") ItemQueryVo queryVo);
}
