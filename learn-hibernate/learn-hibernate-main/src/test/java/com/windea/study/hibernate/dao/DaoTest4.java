package com.windea.study.hibernate.dao;

import com.windea.study.hibernate.domain.Client;
import com.windea.utils.hibernate.HibernateUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.jupiter.api.Test;

class DaoTest4 {
	//Criteria和DetachedCriteria的简单使用说明
	//过滤查询结果：criteria.add(Restrictions.xxx())，或者criteria.add(Expression.xxx())
	//使用聚合函数，完成分组和统计功能：criteria.setProjection(Projections.xxx())
	//排序：criteria.addOrder(Order.xxx())

	/** QBC查询 查询所有 */
	@Test
	void testQBCQuery1() {
		var result = HibernateUtils.doTransaction(session -> {
			//NOTE 已过时
			//return session.createCriteria(Client.class).list();
			//NOTE 首先要获取三个重要的对象
			var cb = session.getCriteriaBuilder();
			var query = cb.createQuery(Client.class);
			var rClient = query.from(Client.class);
			//NOTE 等价于from Client
			query.select(rClient);
			return session.createQuery(query).list();
		});
		result.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
	}

	/** QBC查询 条件查询 */
	@Test
	void testQBCQuery2() {
		var result = HibernateUtils.doTransaction(session -> {
			var cb = session.getCriteriaBuilder();
			var query = cb.createQuery(Client.class);
			var rClient = query.from(Client.class);
			//NOTE 等价于from Client where id=1 and name='渡鸦'
			query.select(rClient).where(
					cb.equal(rClient.get("id"), 1),
					cb.equal(rClient.get("name"), "渡鸦")
			);
			return session.createQuery(query).list();
		});
		result.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
	}

	/** QBC查询 模糊查询 */
	@Test
	void testQBCQuery3() {
		var result = HibernateUtils.doTransaction(session -> {
			var cb = session.getCriteriaBuilder();
			var query = cb.createQuery(Client.class);
			var rClient = query.from(Client.class);
			//NOTE 等价于from Client where name like '%渡鸦%'
			query.select(rClient).where(
					cb.like(rClient.get("name"), "%鸦%")
			);
			return session.createQuery(query).list();
		});
		result.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
	}

	/** QBC查询 排序查询 */
	@Test
	void testQBCQuery4() {
		var result = HibernateUtils.doTransaction(session -> {
			var cb = session.getCriteriaBuilder();
			var query = cb.createQuery(Client.class);
			var rClient = query.from(Client.class);
			//NOTE 等价于from Client order by name
			query.select(rClient).orderBy(
					cb.asc(rClient.get("name"))
			);
			return session.createQuery(query).list();
		});
		result.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
	}

	/** QBC查询 分页查询 */
	void testQBCQuery5() {
		var result = HibernateUtils.doTransaction(session -> {
			var cb = session.getCriteriaBuilder();
			var query = cb.createQuery(Client.class);
			var rClient = query.from(Client.class);
			//NOTE 等价于from Client limit 0,5
			query.select(rClient);
			return session.createQuery(query).setFirstResult(0).setMaxResults(5).list();
		});
		result.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
	}

	/** QBC查询 聚合函数的使用 */
	void testQBCQuery6() {
		var result = HibernateUtils.doTransaction(session -> {
			var cb = session.getCriteriaBuilder();
			var query = cb.createQuery(Long.class);
			var rClient = query.from(Client.class);
			query.select(cb.count(rClient.get("*")));
			return session.createQuery(query).uniqueResult();
		});
		System.out.println(result);
	}

	/** QBC查询 离线查询 */
	void testQBCQuery7() {
		var result = HibernateUtils.doTransaction(session -> {
			//创建对象
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Client.class);
			//最终查询时才需要用到session
			var criteria = detachedCriteria.getExecutableCriteria(session);
			return criteria.list();
		});
		System.out.println(result);
	}
}
