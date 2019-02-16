package com.windea.study.hibernate.dao;

import com.windea.study.hibernate.domain.*;
import com.windea.utility.base.annotation.Tested;
import com.windea.utils.hibernate.HibernateUtils;
import org.junit.jupiter.api.Test;

public class DaoTest2 {

	@Test
	void testOtmAdd() {
		//常规写法
		HibernateUtils.doTransaction(session -> {
			//STEP 创建客户和联系人对象
			var client1 = new Client("渡鸦", "vip", "12345");
			var linkMan1 = new LinkMan("渡鸦12345", "女", "12345");
			//STEP 双向关联
			client1.getLinkManSet().add(linkMan1);
			linkMan1.setClient(client1);
			//STEP 保存到数据库
			session.save(client1);
			session.save(linkMan1);
		});
		HibernateUtils.closeSessionFactory();
	}

	@Test
	void testOtmAdd2() {
		//简化写法：设置cascade属性
		HibernateUtils.doTransaction(session -> {
			//STEP 创建客户和联系人对象
			var client1 = new Client("太阳骑士", "薪王", "12580");
			var linkMan1 = new LinkMan("索拉尔", "男", "12580");
			client1.getLinkManSet().add(linkMan1);
			//STEP 保存到数据库
			session.save(client1);
		});
		HibernateUtils.closeSessionFactory();
	}

	@Test
	void testOtmDelete() {
		//同时也删除相关联的linkMan
		HibernateUtils.doTransaction(session -> {
			var client = session.get(Client.class, 1);
			session.delete(client);
		});
	}

	@Test
	void testMtm() {
		HibernateUtils.getSessionFactory();
	}

	@Test
	@Tested(condition = "cascade = CascadeType.ALL")
	void testMtmAdd() {
		//NOTE 如果使用注解，必须指明cascade = CascadeType.ALL才能通过测试
		//NOTE 如果指明cascade = CascadeType.PERSIST|MERGE，必须首先保存role
		HibernateUtils.doTransaction(session -> {
			var user1 = new User2("张三", "123");
			var user2 = new User2("李四", "123");
			var role1 = new Role("流氓", "流氓");
			var role2 = new Role("强盗", "强盗");
			user1.getRoleSet().add(role1);
			user2.getRoleSet().add(role2);
			session.save(user1);
			session.save(user2);
		});
	}

	@Test
	@Tested
	void testMtmAdd2() {
		HibernateUtils.doTransaction(session -> {
			var user1 = new User2("张三", "123");
			var user2 = new User2("李四", "123");
			var role1 = new Role("流氓", "流氓");
			var role2 = new Role("强盗", "强盗");
			session.save(user1);
			session.save(user2);
			session.save(role1);
			session.save(role2);
			user1.getRoleSet().add(role1);
			user2.getRoleSet().add(role2);
		});
	}

	@Test
	void testMtmUpdate() {
		HibernateUtils.doTransaction(session -> {
			var user1 = session.get(User2.class, 1);
			var role2 = session.get(Role.class, 2);
			user1.getRoleSet().add(role2);
		});
	}
}
