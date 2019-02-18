package com.windea.study.thymeleaf.demo.base.domain;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;

public class Animal implements Serializable {
	private static final long serialVersionUID = -7062258679376158555L;

	private Integer id;

	@NotBlank(message = "动物名: 不能为空或空格")
	private String name;

	@Range(min = 1, message = "数量: 必须大于0")
	@NotNull(message = "数量: 不能为空")
	private Integer count;

	@Size(max = 10, message = "备注: 长度不能超过10个字符")
	private String memo;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}

