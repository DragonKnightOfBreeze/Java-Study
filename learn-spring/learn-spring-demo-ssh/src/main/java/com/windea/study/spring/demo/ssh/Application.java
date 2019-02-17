/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.study.spring.demo.ssh;

import com.windea.study.spring.demo.ssh.config.AppConfig;
import com.windea.study.spring.demo.ssh.config.DatabaseConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;

/**
 * Web项目的初始化器
 * <br>一旦继承这个接口，Spring就会自动解析并进行配置。
 */
public class Application implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext container) {
		//创建rootContext
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		//注册设置类
		rootContext.register(DatabaseConfig.class, AppConfig.class);
		//管理rootContext的生命周期，使之在服务器启动时就创建
		container.addListener(new ContextLoaderListener(rootContext));
	}
}
