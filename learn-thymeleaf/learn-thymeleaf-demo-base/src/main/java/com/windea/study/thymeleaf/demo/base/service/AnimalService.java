package com.windea.study.thymeleaf.demo.base.service;

import com.windea.study.thymeleaf.demo.base.domain.Animal;
import com.windea.study.thymeleaf.demo.base.mapper.IAnimalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnimalService implements IAnimalService {

	private final IAnimalMapper animalMapper;

	@Autowired
	public AnimalService(IAnimalMapper animalMapper) {this.animalMapper = animalMapper;}


	@Override
	public int insert(Animal animal) {
		return animalMapper.insert(animal);
	}

	@Override
	public int insertSelective(Animal animal) {
		return animalMapper.insertSelective(animal);
	}

	@Override
	public int updateById(Animal animal) {
		return animalMapper.updateById(animal);
	}


	@Override
	public int updateByIdSelective(Animal animal) {
		return animalMapper.updateByIdSelective(animal);
	}


	@Override
	public int deleteById(Integer id) {
		return animalMapper.deleteById(id);
	}


	@Override
	public Animal findById(Integer id) {
		return animalMapper.findById(id);
	}

	@Override
	public List<Animal> findAll() {
		return animalMapper.findAll();
	}

	@Override
	public List<Animal> findByName(String name) {
		return animalMapper.findByName(name);
	}

	@Override
	public List<Animal> findByNameLike(String likeName) {
		return animalMapper.findByNameLike(likeName);
	}


}
