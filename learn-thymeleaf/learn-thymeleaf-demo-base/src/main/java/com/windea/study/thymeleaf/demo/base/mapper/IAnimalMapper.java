package com.windea.study.thymeleaf.demo.base.mapper;

import com.windea.study.thymeleaf.demo.base.domain.Animal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IAnimalMapper {
	int insert(@Param("animal") Animal animal);

	//如果参数为null、空、空格时，则不插入对应的字段
	int insertSelective(@Param("animal") Animal animal);


	int updateById(@Param("animal") Animal animal);

	int updateByIdSelective(@Param("animal") Animal animal);


	int deleteById(@Param("id") Integer id);


	Animal findById(@Param("id") Integer id);

	List<Animal> findAll();

	List<Animal> findByName(@Param("name") String name);

	List<Animal> findByNameLike(@Param("likeName") String likeName);


}
