package com.windea.study.mybatis.main.day01.mapper;

import com.windea.study.mybatis.main.day01.domain.ExtendedUser;
import com.windea.study.mybatis.main.day01.domain.User;
import com.windea.study.mybatis.main.day01.domain.view.UserQuery;

import java.util.List;

/**
 * 用户管理的mapper接口
 */
public interface UserMapper {
	/**
	 * 根据id查询用户信息。
	 */
	User findUserById(int id) throws Exception;

	/**
	 * 根据用户名模糊查询用户信息。
	 */
	List<User> findUserByUsername(String Username) throws Exception;

	/**
	 * 查询所有的用户信息。
	 */
	List<User> findAllUser() throws Exception;

	/**
	 * 插入用户信息。
	 */
	void insertUser(User user) throws Exception;

	/**
	 * 删除用户信息。
	 */
	void deleteUserById(int id) throws Exception;

	/**
	 * 更新用户信息。
	 */
	void updateUser(User user) throws Exception;

	/**
	 * 高级查询。
	 * @param query 查询条件
	 */
	ExtendedUser findUserByConditions(UserQuery query) throws Exception;

	/**
	 * 根据id查询用户信息。使用resultMap。
	 */
	User findUserById2(int id) throws Exception;

	/**
	 * 修改过的高级查询。
	 * @param query 查询条件
	 */
	ExtendedUser findUserByConditions2(UserQuery query) throws Exception;

	ExtendedUser findUserByMultiId(UserQuery query) throws Exception;
}
