package com.windea.study.mybatis.main.day02.mapper;

import com.windea.study.mybatis.main.day02.domain.M2M_User;
import com.windea.study.mybatis.main.day02.domain.User;

import java.util.List;

public interface UserMapper {
	/**
	 * 根据id查询用户信息。
	 */
	User findUserById(int id) throws Exception;

	/**
	 * 查询用户以及用户所购买的商品信息。
	 */
	List<M2M_User> findUserInDetail() throws Exception;
}
