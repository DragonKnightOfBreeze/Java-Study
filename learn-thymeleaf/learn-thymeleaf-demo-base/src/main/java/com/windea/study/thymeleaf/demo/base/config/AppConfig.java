package com.windea.study.thymeleaf.demo.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 核心配置类
 */
@Configuration
@ComponentScan({
		"com.windea.study.thymeleaf.demo.base.service",
		"com.windea.study.thymeleaf.demo.base.domain"
})
@MapperScan("com.windea.study.thymeleaf.demo.base.mapper")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AppConfig {

}
