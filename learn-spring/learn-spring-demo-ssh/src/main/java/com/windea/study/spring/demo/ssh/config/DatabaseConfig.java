/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.study.spring.demo.ssh.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.windea.study.spring.demo.ssh.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 项目数据库的设置类
 */
//表示这个类是一个配置类
@Configuration
@PropertySource("classpath:/database.properties")
public class DatabaseConfig {
	private final Environment env;

	@Autowired
	public DatabaseConfig(Environment env) {this.env = env;}

	/**
	 * 数据源的Bean。
	 */
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass(env.getProperty("datasource.driver"));
		//NOTE 需要设置参数：serverTimezone=GMT%2B8（GMT+8）
		ds.setJdbcUrl(env.getProperty("datasource.url"));
		ds.setUser(env.getProperty("datasource.user"));
		ds.setPassword(env.getProperty("datasource.password"));
		return ds;
	}

	/**
	 * Hibernate的sessionFactory的Bean。
	 */
	@Bean
	public FactoryBean<SessionFactory> sessionFactory() throws PropertyVetoException {
		//创建封装后的sessionFactory
		var sessionFactory = new LocalSessionFactoryBean();
		//设置数据源
		sessionFactory.setDataSource(dataSource());
		//加载核心配置文件
		sessionFactory.setConfigLocation(new ClassPathResource("/hibernate.cfg.xml"));
		//注册JPA注解类
		sessionFactory.setAnnotatedClasses(User.class);
		return sessionFactory;
	}

	/**
	 * Hibernate模版的Bean。
	 */
	@Bean
	public HibernateTemplate hibernateTemplate() throws Exception {
		return new HibernateTemplate(sessionFactory().getObject());
	}

	/**
	 * Hibernate数据源事务管理器的Bean。
	 */
	@Bean
	public HibernateTransactionManager hibernateTransactionManager() throws Exception {
		return new HibernateTransactionManager(sessionFactory().getObject());
	}
}
