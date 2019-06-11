package com.windea.study.mybatis.main.integration.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目的设置类
 */
//表示这个类是一个配置类
@Configuration
//自动扫描组件（属性值：要扫描的包名，一般是action、service和dao，可能需要config）
@ComponentScan({
	//"com.windea.study.mybatis.main.integration.config",
	"com.windea.study.mybatis.main.integration.service",
	"com.windea.study.mybatis.main.integration.dao"
})
//允许事务管理
@EnableTransactionManagement
//允许自动Aspect代理
@EnableAspectJAutoProxy
public class AppConfig {

}
