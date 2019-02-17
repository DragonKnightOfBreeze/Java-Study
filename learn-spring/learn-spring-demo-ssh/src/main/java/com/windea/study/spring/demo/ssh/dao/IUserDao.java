/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.study.spring.demo.ssh.dao;

import com.windea.study.spring.demo.ssh.domain.User;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 用户的dao接口
 */
public interface IUserDao {
	/**
	 * 添加用户。
	 * @param user 指定的用户
	 */
	void add(@NonNull User user);

	/**
	 * 删除用户。
	 * @param id 指定的用户id
	 */
	void delete(@NonNull Integer id);

	/**
	 * 更新用户。
	 * @param user 指定的用户
	 */
	void update(@NonNull User user);

	/**
	 * 查找单个用户。
	 * @param id 指定的用户id
	 */
	User get(@NonNull Integer id);

	/**
	 * 查找所有用户。
	 */
	List<User> getAll();

	/**
	 * 使用HQL，根据用户名查找单个用户。
	 * <br>无法进行分页查询。
	 */
	@Deprecated
	User find(@NonNull String username);

	/**
	 * 使用HQL，根据用户名进行模糊查询。
	 * <br>无法进行分页查询。
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	List<User> search(@NonNull String username);

	/**
	 * 使用HQL，统计用户总数。
	 */
	@Deprecated
	Integer count();

	/**
	 * 使用Criteria，根据用户名查找单个用户。
	 * <br>通过方法重载实现了分页查询。
	 */
	User find2(@NonNull String username);

	/**
	 * 使用Criteria，根据用户名进行模糊查询。
	 * <br>通过方法重载实现了分页查询。
	 */
	@SuppressWarnings("unchecked")
	List<User> search2(@NonNull String username);

	/**
	 * 使用Criteria，统计用户总数。
	 */
	Integer count2();
}
