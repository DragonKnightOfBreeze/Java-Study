package com.windea.study.springmvc.main.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//"com.windea.study.springmvc.main.config",
@ComponentScan("com.windea.study.springmvc.main.service")
@MapperScan(
		"com.windea.study.springmvc.main.mapper"
)
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AppConfig {

}
