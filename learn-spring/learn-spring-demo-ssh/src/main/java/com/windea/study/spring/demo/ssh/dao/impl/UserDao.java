/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.study.spring.demo.ssh.dao.impl;

import com.windea.study.spring.demo.ssh.dao.IUserDao;
import com.windea.study.spring.demo.ssh.domain.User;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户的dao类
 */
//表示这个类被Spring托管，且在持久层
@Repository
public class UserDao implements IUserDao {
	private final HibernateTemplate template;

	@Autowired
	public UserDao(HibernateTemplate template) {
		this.template = template;
	}

	/**
	 * 添加用户。
	 * @param user 指定的用户
	 */
	@Override
	public void add(@NonNull User user) {
		template.save(user);
		System.out.println("添加用户成功");
	}

	/**
	 * 删除用户。
	 * @param id 指定的用户id
	 */
	@Override
	public void delete(@NonNull Integer id) {
		User user = template.get(User.class, id);
		if(user == null)
			return;
		template.delete(user);
	}

	/**
	 * 更新用户。
	 * @param user 指定的用户
	 */
	@Override
	public void update(@NonNull User user) {
		//template.saveOrUpdate(user);
		template.save(user);
	}

	/**
	 * 查找单个用户。
	 * @param id 指定的用户id
	 */
	@Override
	public User get(@NonNull Integer id) {
		//return template.get(User.class, id);
		return template.load(User.class, id);
	}

	/**
	 * 查找所有用户。
	 */
	@Override
	public List<User> getAll() {
		return template.loadAll(User.class);
	}


	/**
	 * 使用HQL，根据用户名查找单个用户。
	 * <br>无法进行分页查询。
	 */
	@Override
	@Deprecated
	public User find(@NonNull String username) {
		return (User) template.find("from User where username=?1", username).get(0);
	}

	/**
	 * 使用HQL，根据用户名进行模糊查询。
	 * <br>无法进行分页查询。
	 */
	@Override
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<User> search(@NonNull String username) {
		String searchField = "%" + username + "%";
		return (List<User>) template.find("from User where username like ?1", searchField).listIterator();
	}

	/**
	 * 使用HQL，统计用户总数。
	 */
	@Override
	@Deprecated
	public Integer count() {
		return (Integer) template.find("select count(*) from User").get(0);
	}


	//NOTE Criteria和DetachedCriteria的简单使用说明
	//过滤查询结果：criteria.add(Restrictions.xxx())，或者criteria.add(Expression.xxx())
	//使用聚合函数，完成分组和统计功能：criteria.setProjection(Projections.xxx())
	//排序：criteria.addOrder(Order.xxx())

	/**
	 * 使用Criteria，根据用户名查找单个用户。
	 * <br>通过方法重载实现了分页查询。
	 */
	@Override
	public User find2(@NonNull String username) {
		return (User) template.findByCriteria(DetachedCriteria.forClass(User.class)
				.add(Restrictions.eq("username", username))
				.addOrder(Order.asc("id"))
		).get(0);
	}

	/**
	 * 使用Criteria，根据用户名进行模糊查询。
	 * <br>通过方法重载实现了分页查询。
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<User> search2(@NonNull String username) {
		String searchField = "%" + username + "%";
		return (List<User>) template.findByCriteria(DetachedCriteria.forClass(User.class)
				.add(Restrictions.like("username", searchField))
				.addOrder(Order.asc("id"))
		).listIterator();
	}

	/**
	 * 使用Criteria，统计用户总数。
	 */
	@Override
	public Integer count2() {
		return (Integer) template.findByCriteria(DetachedCriteria.forClass(User.class)
				.setProjection(Projections.count("*"))
		).get(0);
	}
}
