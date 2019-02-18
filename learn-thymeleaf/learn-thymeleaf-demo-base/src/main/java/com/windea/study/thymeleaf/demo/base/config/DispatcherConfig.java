package com.windea.study.thymeleaf.demo.base.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;

/**
 * SpringMVC的配置类
 * <br>实现WebMvcConfigurer接口以进行自定义配置，也可以使用默认配置。
 */
@Configuration
@ComponentScan("com.windea.study.thymeleaf.demo.base.controller")
@EnableWebMvc
public class DispatcherConfig extends WebMvcConfigurationSupport {

	/*STEP Thymeleaf的相关配置；*/

	/**
	 * Thymeleaf的Spring资源模版解析器的bean。
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		var templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/pages");
		//templateResolver.setSuffix(".html");
		//更改html后不需要重启服务器
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	/**
	 * Thymeleaf的Spring模版引擎的bean。
	 */
	@Bean
	public ISpringTemplateEngine templateEngine() {
		var templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	/*STEP SpringMVC的基本配置*/

	/**
	 * 视图解析器的bean。
	 * <br>使用Thymeleaf模版引擎。
	 */
	@Bean
	public ViewResolver viewResolver() {
		var viewResolver = new ThymeleafViewResolver();
		viewResolver.setOrder(1);
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}


	/**
	 * 文本信息资源的bean。
	 */
	@Bean
	public MessageSource textMessageSource() {
		var messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("message/text/textMessages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(120);
		return messageSource;
	}

	/**
	 * 校验错误信息资源的bean。
	 */
	@Bean
	public MessageSource validationMessageSource() {
		var messageSource = new ReloadableResourceBundleMessageSource();
		//配置校验信息的属性文件
		messageSource.setBasenames("message/validation/validationMessages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(120);
		return messageSource;
	}

	/**
	 * 校验器的bean。
	 */
	@Bean
	public Validator validator() {
		var bean = new LocalValidatorFactoryBean();
		bean.setProviderClass(HibernateValidator.class);
		bean.setValidationMessageSource(validationMessageSource());
		return bean;
	}

	/**
	 * 配置校验器。
	 */
	@Override
	public Validator getValidator() {
		return validator();
	}

	/**
	 * 配置视图解析器。
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(viewResolver());
	}

	/**
	 * 配置自定义异常处理器。
	 */
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(handlerExceptionResolver());
	}

	/**
	 * 配置静态资源处理器。
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**", "/assets/**", "/temp/**");
	}
}
