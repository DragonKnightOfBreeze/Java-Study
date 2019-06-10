package com.windea.study.springboot.main.service.impl;

import com.windea.study.springboot.main.domain.Weapon;
import com.windea.study.springboot.main.domain.WeaponQueryVoKt;
import com.windea.study.springboot.main.repository.WeaponRepository;
import com.windea.study.springboot.main.service.WeaponService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class WeaponServiceImpl implements WeaponService {
	private final WeaponRepository repository;

	public WeaponServiceImpl(WeaponRepository repository) {
		this.repository = repository;
	}

	@Override
	public Weapon getById(Integer id) {
		return repository.getById(id);
	}

	@Override
	public Weapon getByName(String name) {
		return repository.getByName(name);
	}

	@Override
	public List<Weapon> getAll() {
		return repository.getAll();
	}

	@Override
	public List<Weapon> queryByCategory(String category) {
		return repository.queryByCategory(category);
	}

	@Override
	public List<Weapon> queryDistinctByTags(Set<String> tags) {
		return repository.queryDistinctByTags(tags);
	}

	//基于Specification接口的高级查询，以查询视图对象为参数，将其转化为Specification
	@Override
	public List<Weapon> queryDistinct(WeaponQueryVoKt weaponQueryVo) {
		return repository.queryDistinct(weaponQueryVo.toSpecification());
	}
}
