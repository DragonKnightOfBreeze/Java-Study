package com.windea.study.thymeleaf.demo.base;

import com.windea.study.thymeleaf.demo.base.config.*;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Web项目的初始化器。
 */
@Order(1)
public class Application extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Filter[] getServletFilters() {
		//添加编码过滤器
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true);
		return new Filter[]{encodingFilter};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{DatabaseConfig.class, AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{DispatcherConfig.class};
	}
}
