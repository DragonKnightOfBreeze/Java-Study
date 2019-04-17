package com.windea.study.springboot.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBeanTest {
	@Autowired ApplicationContext context;

	@Test
	public void test1() {
		var testBean = context.getBean(TestBean.class);
		System.out.println(testBean.getName());
		System.out.println(testBean.getWeapon());
	}
}