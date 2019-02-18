package com.windea.study.thymeleaf.demo.base.service;

import com.windea.study.thymeleaf.demo.base.domain.Animal;

import java.util.List;

public interface IAnimalService {

	int insert(Animal animal);

	int insertSelective(Animal animal);

	int updateById(Animal animal);

	int updateByIdSelective(Animal animal);


	int deleteById(Integer id);


	Animal findById(Integer id);

	List<Animal> findAll();

	List<Animal> findByName(String name);

	List<Animal> findByNameLike(String likeName);
}
