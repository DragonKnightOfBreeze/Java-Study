package com.windea.study.springmvc.main.config;

import com.github.pagehelper.PageInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.windea.utility.utils.base.EncodingUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 项目数据库的设置类
 * @noinspection Duplicates
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
		try {
			var properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/c3p0.properties"));
			properties = EncodingUtils.encoding(properties);
			dataSource.setProperties(properties);
		} catch(Exception ignored) {}
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
		bean.setPlugins(new Interceptor[]{
				pageInterceptor()
		});
		bean.setConfigLocation(new ClassPathResource("/SqlMapConfig.xml"));
		return bean.getObject();
	}

	/**
	 * PageHelper的拦截器的Bean。
	 * <p>如何配置属性文件：
	 * <a href="https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md">Github</a>
	 */
	@Bean
	public PageInterceptor pageInterceptor() {
		var pageInterceptor = new PageInterceptor();
		try {
			var properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/pagehelper.properties"));
			properties = EncodingUtils.encoding(properties);
			pageInterceptor.setProperties(properties);
		} catch(Exception ignored) {}
		return pageInterceptor;
	}
}
