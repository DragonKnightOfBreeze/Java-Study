package com.windea.study.mybatis.main.day01.dao;

import com.windea.study.mybatis.main.day01.domain.User;

import java.util.List;

/**
 * 用户管理的dao接口
 */
public interface UserDao {
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
}
