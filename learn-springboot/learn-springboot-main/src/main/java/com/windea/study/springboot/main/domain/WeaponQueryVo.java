package com.windea.study.springboot.main.domain;

import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Set;

public class WeaponQueryVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String keyword;
	private Set<String> categories;
	private Set<String> tags;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}


	//NOTE 在这里实现将查询条件转化为实体类对应的Specification接口的代码
	public Specification<Weapon> toSpecification() {
		//Specification实际上是一个lambda表达式 (root,query,builder) -> predicate
		//组合这三个参数构造criteria查询，只包括where子句，排序等功能另外实现

		//查询条件：
		// root.name = this.name &&
		// root.name like this.field &&
		// root.category in this.categories &&
		// root.tags = this.tags
		return (root, query, builder) ->
			builder.and(
				builder.equal(root.get("name"), this.name),
				builder.like(root.get("name"), this.keyword),
				root.get("category").in(this.categories),
				builder.equal(root.get("tags"), this.tags)
			);

	}
}
