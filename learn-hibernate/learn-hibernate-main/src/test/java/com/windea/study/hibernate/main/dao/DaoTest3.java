package com.windea.study.hibernate.main.dao;

import com.windea.study.hibernate.main.domain.Client;
import com.windea.utility.utils.hibernate.HibernateUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

class DaoTest3 {

	/** 测试对象导航查询 */
	@Test
	void testSelect1() {
		HibernateUtils.doTransaction(session -> {
			//STEP 根据id查询客户
			var client1 = session.get(Client.class, 1);
			//STEP 得到客户对应的联系人集合
			var linkManSet = client1.getLinkManSet();
			//输出
			System.out.println(linkManSet.size());
			linkManSet.forEach(System.out::println);
		});
	}

	/** 测试HQL查询 查询所有 */
	@Test
	void testHQLQuery1() {
		HibernateUtils.doTransaction(session -> {
			//STEP 创建Query对象
			var query = session.createQuery("from com.windea.study.hibernate.domain.Client ", Client.class);
			//STEP 调用方法得到结果
			var list = query.list();
			list.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
		});
	}

	/** 测试HQL查询 条件查询 */
	@Test
	void testHQLQuery2() {
		HibernateUtils.doTransaction(session -> {
			var query = session.createQuery(
					"from com.windea.study.hibernate.domain.Client where id=?1 and name=?2", Client.class);
			//参数：与语句中的?n对应的索引，查询参数
			query.setParameter(1, 1);
			query.setParameter(2, "渡鸦");
			var list = query.list();
			list.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
		});
	}

	/** 测试HQL查询 模糊查询 */
	@Test
	void testHQLQuery3() {
		HibernateUtils.doTransaction(session -> {
			var query = session.createQuery(
					"from com.windea.study.hibernate.domain.Client where name like ?1", Client.class);
			//_匹配一个字符，%匹配0个或多个字符
			query.setParameter(1, "%鸦%");
			var list = query.list();
			list.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
		});
	}

	/** 测试HQL查询 排序查询 */
	@Test
	void testHQLQuery4() {
		HibernateUtils.doTransaction(session -> {
			//默认升序
			var query = session.createQuery(
					"from com.windea.study.hibernate.domain.Client order by id asc", Client.class);
			var list = query.list();
			list.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
		});
	}

	/** 测试HQL查询 分页查询 */
	@Test
	void testHQLQuery5() {
		HibernateUtils.doTransaction(session -> {
			//不能在hql里面使用limit关键字
			var query = session.createQuery("from com.windea.study.hibernate.domain.Client", Client.class);
			//设置分页参数
			query.setFirstResult(0).setMaxResults(5);
			var list = query.list();
			list.forEach(e -> System.out.println(e.getId() + "\t" + e.getName()));
		});
	}


	/** 测试HQL查询 投影查询（就是查询部分字段的值）（用的不是很多） */
	@Test
	void testHQLQuery6() {
		HibernateUtils.doTransaction(session -> {
			var query = session.createQuery("select id,name from com.windea.study.hibernate.domain.Client");
			List<?> list = query.list();
			list.forEach(System.out::println);
		});
	}

	/**
	 * 测试HQL查询 聚集函数的使用
	 * <br>常用的聚集函数：sum,sub,avg,count,max,min
	 */
	@Test
	void testHQLQuery7() {
		HibernateUtils.doTransaction(session -> {
			var query = session
					.createQuery("select count(*) from com.windea.study.hibernate.domain.Client", Integer.class);
			var result = query.uniqueResult();
			//			var query = session.createQuery("select count(*) from Client",Long.class);
			//			int result = query.uniqueResult().intValue();
			System.out.println(result);
		});
	}
}
