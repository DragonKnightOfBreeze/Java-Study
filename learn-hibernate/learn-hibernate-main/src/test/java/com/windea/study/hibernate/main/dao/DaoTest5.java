package com.windea.study.hibernate.main.dao;

import com.windea.utility.utils.hibernate.HibernateUtils;
import org.junit.jupiter.api.Test;

class DaoTest5 {
	/** 测试HQL多表查询 内连接 */
	@Test
	void testHQLMultiQuery1() {
		var result = HibernateUtils.doTransaction(session -> {
			//返回的是对象数组列表的形式
			var query = session.createQuery("from Client c inner join  c.linkManSet");
			return query.list();
		});
	}

	/** 测试HQL多表查询 迫切内连接 */
	@Test
	void testHQLMultiQuery2() {
		var result = HibernateUtils.doTransaction(session -> {
			//返回的是对象数组列表的形式
			var query = session.createQuery("from Client c inner join fetch c.linkManSet");
			return query.list();
		});
	}
}
