package com.windea.study.springmvc.domain;

import java.io.Serializable;

/**
 * 用户查询信息
 * <br>为了系统的可拓展性，一般不会对原始的实体类进行扩展。
 */
public class UserQueryVo implements Serializable {
	private static final long serialVersionUID = 7562931721098631794L;

	private UserEx userEx;

	public UserEx getUserEx() {
		return userEx;
	}

	public void setUserEx(UserEx userEx) {
		this.userEx = userEx;
	}
}
