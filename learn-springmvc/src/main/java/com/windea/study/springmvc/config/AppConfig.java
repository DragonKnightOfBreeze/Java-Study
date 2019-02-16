package com.windea.study.springmvc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//"windea.lesson.springmvc.config",
@ComponentScan("windea.lesson.springmvc.service")
@MapperScan(
		"windea.lesson.springmvc.mapper"
)
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AppConfig {

}
