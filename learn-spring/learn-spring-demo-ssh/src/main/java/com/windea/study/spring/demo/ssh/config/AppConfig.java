/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.study.spring.demo.ssh.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目的设置类
 */
//表示这个类是一个配置类
@Configuration
//自动扫描组件（属性值：要扫描的包名，一般是action、service和dao，可能需要config）
@ComponentScan({
		"windea.demo.sshdemo1.web.action",
		"windea.demo.sshdemo1.service",
		"windea.demo.sshdemo1.dao",
		"windea.demo.sshdemo1.config"
})
//允许事务管理
@EnableTransactionManagement
//允许自动Aspect代理
@EnableAspectJAutoProxy
public class AppConfig {

}
