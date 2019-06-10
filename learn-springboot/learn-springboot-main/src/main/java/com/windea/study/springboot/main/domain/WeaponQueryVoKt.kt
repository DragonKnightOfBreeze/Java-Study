package com.windea.study.springboot.main.domain

import org.springframework.data.jpa.domain.Specification

import java.io.Serializable

class WeaponQueryVoKt constructor(
	val name: String,
	val keyword: String,
	val categories: Set<String>,
	val tags: Set<String>
) : Serializable {
	fun toSpecification(): Specification<Weapon> {
		return Specification { root, _, builder ->
			builder.and(
				builder.equal(root.get<String>("name"), this.name),
				builder.like(root.get<String>("name"), this.keyword),
				root.get<String>("category").`in`(this.categories),
				builder.equal(root.get<String>("tags"), this.tags)
			)
		}
	}
}
