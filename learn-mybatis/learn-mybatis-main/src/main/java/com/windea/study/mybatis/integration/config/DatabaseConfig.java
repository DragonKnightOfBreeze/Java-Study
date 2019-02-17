/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.study.mybatis.integration.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

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
	public DataSource dataSource() throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getProperty("database.driver"));
		dataSource.setJdbcUrl(env.getProperty("database.url"));
		dataSource.setUser(env.getProperty("database.user"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
	}

	/**
	 * Jdbc模版的Bean。
	 */
	@Bean
	public JdbcTemplate jdbcTemplate() throws Exception {
		return new JdbcTemplate(dataSource());
	}

	/**
	 * 事务管理器的Bean。
	 */
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() throws Exception {
		return new DataSourceTransactionManager(dataSource());
	}


	/**
	 * MyBatis的sqlSessionFactory的Bean。
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		var bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setConfigLocation(new ClassPathResource("/SqlMapConfig_Integration.xml"));
		return bean.getObject();
	}

	///**
	// * MyBatis的mapper代理对象的bean。
	// * <p>可以根据mapper接口生成mapper代理对象。
	// */
	//@Bean
	//public MapperFactoryBean<UserMapper> userMapper() throws Exception {
	//	var userMapperBean = new MapperFactoryBean<>(UserMapper.class);
	//	userMapperBean.setSqlSessionFactory(sqlSessionFactory().getObject());
	//	return userMapperBean;
	//}
}
