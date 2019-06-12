package com.windea.study.spring.demo.ssm.config;

import com.windea.study.spring.demo.ssm.converter.DateConverter;
import com.windea.study.spring.demo.ssm.exception.AppHandlerExceptionResolver;
import com.windea.study.spring.demo.ssm.interceptor.LogInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;
import java.util.Objects;

/**
 * SpringMVC的配置类
 * <br>实现WebMvcConfigurer接口以进行自定义配置，也可以使用默认配置。
 */
@Configuration
@ComponentScan("com.windea.study.spring.demo.ssm.controller")
@EnableWebMvc
public class DispatcherConfig extends WebMvcConfigurationSupport {
	/**
	 * 视图解析器的bean。
	 * <br>解析jsp，默认适用jstl标签，classpath下面需要有jstl的包
	 */
	@Bean
	public ViewResolver viewResolver() {
		var viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		//让Spring的所有bean都能支持jstl和el
		viewResolver.setExposeContextBeansAsAttributes(true);

		//配置jsp路径的前缀和后缀（同时需要调整viewName）
		viewResolver.setPrefix("/WEB-INF/pages");
		//viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * 校验错误信息资源的bean。
	 */
	@Bean
	public MessageSource validationMessageSource() {
		var messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("message/validation/validationMessages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(120);
		return messageSource;
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
	 * 全局异常处理器的bean。
	 */
	@Bean
	public AppHandlerExceptionResolver appHandlerExceptionResolver() {
		return new AppHandlerExceptionResolver();
	}

	/**
	 * 文件上传解析器的bean。
	 */
	@Bean
	public MultipartResolver multipartResolver() throws Exception {
		var multipartResolver = new CommonsMultipartResolver();
		//设置编码和缓存目录
		multipartResolver.setDefaultEncoding("UTF-8");
		var context = Objects.requireNonNull(getServletContext());
		multipartResolver.setUploadTempDir(new ServletContextResource(context, "/temp"));
		//必须要配置以下两项
		multipartResolver.setMaxInMemorySize(30 * 1024 * 1024);
		multipartResolver.setMaxUploadSizePerFile(3 * 1024 * 1024);
		multipartResolver.setMaxUploadSize(30 * 1024 * 102);
		return multipartResolver;
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
		var viewResolver = new InternalResourceViewResolver();
		registry.viewResolver(viewResolver());
	}

	/**
	 * 配置自定义异常处理器。
	 */
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(appHandlerExceptionResolver());
	}


	/**
	 * 配置类型转化器和格式化器。
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new DateConverter());
	}

	/**
	 * 配置静态资源处理器。
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**", "/assets/**", "/temp/**");
	}

	/**
	 * 配置拦截器。
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
	}
}
