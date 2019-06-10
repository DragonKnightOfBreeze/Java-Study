package com.windea.study.springboot.main.service;

import com.windea.study.springboot.main.domain.Weapon;
import com.windea.study.springboot.main.domain.WeaponQueryVoKt;

import java.util.List;
import java.util.Set;

public interface WeaponService {
	Weapon getById(Integer id);

	Weapon getByName(String name);

	List<Weapon> getAll();

	List<Weapon> queryByCategory(String category);

	List<Weapon> queryDistinctByTags(Set<String> tags);

	//基于Specification接口的高级查询，以查询视图对象为参数，将其转化为Specification
	List<Weapon> queryDistinct(WeaponQueryVoKt weaponQueryVo);
}
