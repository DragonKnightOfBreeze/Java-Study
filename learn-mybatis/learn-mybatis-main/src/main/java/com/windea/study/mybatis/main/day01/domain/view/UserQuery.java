package com.windea.study.mybatis.main.day01.domain.view;

import com.windea.study.mybatis.main.day01.domain.ExtendedUser;
import com.windea.study.mybatis.main.day01.domain.User;
import com.windea.utility.base.ext.ObjectExt;

import java.io.Serializable;
import java.util.List;

/**
 * 包装用户查询需要的查询条件
 */
public class UserQuery implements Serializable {
	private static final long serialVersionUID = -2653959957952139016L;

	private ExtendedUser user;

	private List<Integer> idList;

	public User getUser() {
		return user;
	}

	public void setUser(ExtendedUser user) {
		this.user = user;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	@Override
	public boolean equals(Object obj) {
		return ObjectExt.objEquals(this, obj);
	}

	@Override
	public String toString() {
		return ObjectExt.objToString(this);
	}

	@Override
	public int hashCode() {
		return ObjectExt.objHashcode(this);
	}
}
