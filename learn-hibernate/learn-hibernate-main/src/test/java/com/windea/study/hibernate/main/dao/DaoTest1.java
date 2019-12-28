package com.windea.study.hibernate.main.dao;

import com.windea.study.hibernate.main.domain.User;
import com.windea.utility.utils.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.util.List;

class DaoTest1 {
	@Test
	void testAdd() {
		//STEP 1. 加载hibernate核心配置文件
		var cfg = new Configuration();
		//到src下面找到名称是hibernate.cfg.xml的文件，封装进去
		cfg.configure();
		//NOTE 使用注解方式的话，就要加上这一行
		cfg.addAnnotatedClass(User.class);
		//STEP 2. 创建SessionFactory对象
		//读取配置文件内容，创建对象
		//在过程中，根据映射关系，在配置数据库中把表创建出来
		var sessionFactory = cfg.buildSessionFactory();
		//STEP 3. 使用SessionFactory创建session对象
		var session = sessionFactory.openSession();
		//STEP 4. 开启事务
		var transaction = session.beginTransaction();
		//STEP 5. 写具体的事务操作

		User user = new User();
		user.setUserName("Windea");
		user.setPassword("BreezesLanding");
		user.setAddress("Urangel");
		//持久化实体对象，保存到数据库中
		session.save(user);

		//STEP 6. 提交事务
		transaction.commit();
		//STEP 7. 关闭资源
		session.close();
		sessionFactory.close();
	}

	@Test
	void testUpdate() {
		//STEP 1. 加载hibernate核心配置文件
		var cfg = new Configuration();
		//到src下面找到名称是hibernate.cfg.xml的文件，封装进去
		cfg.configure();
		//NOTE 使用注解方式的话，就要加上这一行
		cfg.addAnnotatedClass(User.class);
		//STEP 2. 创建SessionFactory对象
		//读取配置文件内容，创建对象
		//在过程中，根据映射关系，在配置数据库中把表创建出来
		var sessionFactory = cfg.buildSessionFactory();
		//STEP 3. 使用SessionFactory创建session对象
		var session = sessionFactory.openSession();
		//STEP 4. 开启事务
		var transaction = session.beginTransaction();
		//STEP 5. 写具体的事务操作

		User user = session.get(User.class, 1);
		user.setAddress("YetAnotherArea");
		session.update(user);

		//STEP 6. 提交事务
		transaction.commit();
		//STEP 7. 关闭资源
		session.close();
		sessionFactory.close();
	}


	@Test
	void testAdd2() {
		HibernateUtils.doTransaction(session -> {
			var user = new User();
			user.setUserName("EverSong");
			user.setPassword("Shinra");
			user.setAddress(null);
			session.save(user);
		});
	}

	@Test
	void testUpdate2() {
		HibernateUtils.doTransaction(session -> {
			var user = session.get(User.class, 1);
			user.setAddress("Urangel");
			session.update(user);
		});
	}

	@Test
	void testQuery1() {
		var list = HibernateUtils.doTransaction(session -> {
			return session.createQuery("from com.windea.study.hibernate.main.domain.User", User.class).list();
		});

		list.forEach(System.out::println);
	}

	@Test
	void testQuery2() {
		var list = HibernateUtils.doTransaction(session -> {
			//NOTE 已过时
			//return session.createCriteria(User.class).list();
			//NOTE 重要的对象：Root,Path,Selection...
			var query = session.getCriteriaBuilder().createQuery(User.class);
			query.select(query.from(User.class));
			return session.createQuery(query).list();
		});
		list.forEach(System.out::println);
	}

	@Test
	void testQuery3() {
		//返回list集合，默认里面每部分使用对象数组结构
		//		List<Object[]> list = HibernateUtils.doTransaction(session -> {
		//			return session.createSQLQuery("select * from t_user").list();
		//		});
		//		list.forEach(e-> System.out.println(Arrays.toString(e)));

		List<?> list = HibernateUtils.doTransaction(session -> {
			return session.createSQLQuery("select * from t_user").addEntity(User.class).list();
		});
		list.forEach(System.out::println);
	}


	@Test
	void testTransaction() {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()) {
			//开启事务
			transaction = session.beginTransaction();
			//事务操作

			//提交事务
			transaction.commit();
		} catch(Exception e) {
			//回滚事务
			transaction.rollback();

		}
	}

	@Test
	void testTransaction2() {
		try(var session = HibernateUtils.getSessionFactory().openSession()) {
			var transaction = session.beginTransaction();
			try {
				//事务操作

				//提交事务
				transaction.commit();
			} catch(Exception e) {
				e.printStackTrace();
				transaction.rollback();
			}
		}
	}
}
