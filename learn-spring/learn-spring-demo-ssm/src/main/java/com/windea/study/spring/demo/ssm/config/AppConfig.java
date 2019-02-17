package com.windea.study.spring.demo.ssm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 核心配置类
 */
@Configuration
@ComponentScan("com.windea.study.spring.demo.ssm.service")
@MapperScan("com.windea.study.spring.demo.ssm.mapper")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AppConfig {

}
