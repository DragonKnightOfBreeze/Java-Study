package com.windea.study.springboot.main.repository;

import com.windea.study.springboot.main.domain.Weapon;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface WeaponRepository extends JpaRepository<Weapon, Integer> {
	Weapon getById(Integer id);

	Weapon getByName(String name);

	List<Weapon> getAll();

	List<Weapon> queryByCategory(String category);

	List<Weapon> queryDistinctByTags(Set<String> tags);

	//基于Specification接口的高级查询，使用Criteria语法的灵活查询
	List<Weapon> queryDistinct(Specification<Weapon> specification);
}
