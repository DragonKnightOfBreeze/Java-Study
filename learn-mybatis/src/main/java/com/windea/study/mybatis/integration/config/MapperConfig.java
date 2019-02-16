package com.windea.study.mybatis.integration.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//可以使用@MapperScan注解替代
@MapperScan(
		basePackages = "windea.lesson.mybatis.integration.mapper",
		sqlSessionFactoryRef = "sqlSessionFactory"
)
public class MapperConfig {
	///**
	// * 对MyBatis的mapper进行批量扫描。自动获得代理对象并注册。
	// * 自动扫描出来的mapper，bean的id为mapper类名的首字母小写。
	// * 如果扫描多个包，每个包之间用半角逗号分隔。
	// */
	//@Bean
	//public MapperScannerConfigurer mapperScannerConfigurer() throws Exception {
	//	var scanner = new MapperScannerConfigurer();
	//	scanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
	//	scanner.setBasePackage("windea.lesson.mybatis.integration.mapper");
	//	return scanner;
	//}
}
