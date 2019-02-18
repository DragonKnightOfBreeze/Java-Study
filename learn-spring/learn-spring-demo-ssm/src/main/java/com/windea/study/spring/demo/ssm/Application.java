package com.windea.study.spring.demo.ssm;

import com.windea.study.spring.demo.ssm.config.*;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Web项目的初始化器。Spring会自动解析并进行配置。
 *
 * <p>可选方式：
 * <ul>
 * <li>实现{@code WebApplicationInitializer}接口。
 * <br>需要自己添加{@code ContextLoaderListener}和{@code DispatcherServlet}。
 * <br>Idea不能正确解析视图。
 * </li>
 * <li>继承{@code AbstractAnnotationConfigDispatcherServletInitializer}。
 * <br>不需要自己添加上述类/接口。通过重载相关方法进行配置。
 * <br>Idea能够正确解析视图。
 * </li>
 * </ul>
 *
 * <p>可以完全替代web.xml的配置方法，添加Servlet、Listener和Filter，但是idea可能不会正确识别。
 */
@Order(1)
public class Application extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		super.onStartup(container);
		//添加编码过滤器
		container.addFilter("encodingFilter", new CharacterEncodingFilter("utf-8"));
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
