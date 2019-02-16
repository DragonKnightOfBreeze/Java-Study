package com.windea.study.springmvc.config;

import com.windea.study.springmvc.converter.CustomDateConverter;
import com.windea.study.springmvc.exception.AppHandlerExceptionResolver;
import com.windea.study.springmvc.interceptor.LoginInterceptor;
import com.windea.study.springmvc.interceptor.MyInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.ServletContext;
import java.util.List;

/**
 * SpringMVC的配置类
 * <br>实现WebMvcConfigurer接口以进行自定义配置，也可以使用默认配置。
 */
@Configuration
@ComponentScan("windea.lesson.springmvc.controller")
@EnableWebMvc
public class DispatcherConfig implements WebMvcConfigurer {
	private final ServletContext servletContext;

	@Autowired
	public DispatcherConfig(ServletContext servletContext) {this.servletContext = servletContext;}

	///**
	// * 处理器适配器的bean。
	// */
	//@Bean
	//public HandlerAdapter handlerAdapter() {
	//	return new RequestMappingHandlerAdapter();
	//}
	//
	///**
	// * 处理器映射器的bean。
	// */
	//@Bean
	//public HandlerMapping handlerMapping() {
	//	return new RequestMappingHandlerMapping();
	//}

	///**
	// * 类型转化器的bean。
	// */
	//@Bean
	//public FormattingConversionService conversionService() {
	//	var bean = new FormattingConversionServiceFactoryBean();
	//	bean.setConverters(Set.of(new CustomDateConverter()));
	//	return bean.getObject();
	//}

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
	 * 校验器的bean。
	 */
	@Bean
	public Validator validator() {
		var bean = new LocalValidatorFactoryBean();
		bean.setProviderClass(HibernateValidator.class);
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	/**
	 * 校验错误信息资源的bean。
	 */
	@Bean
	public MessageSource messageSource() {
		var messageSource = new ReloadableResourceBundleMessageSource();
		//配置校验信息的属性文件
		messageSource.setBasenames("message/validationMessages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(120);
		return messageSource;
	}

	/**
	 * 全局异常处理器的bean。
	 */
	@Bean
	public HandlerExceptionResolver handlerExceptionResolver() {
		return new AppHandlerExceptionResolver();
	}

	/**
	 * 文件上传解析器的bean。
	 */
	@Bean
	public MultipartResolver multipartResolver() throws Exception {
		var multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setUploadTempDir(new ServletContextResource(servletContext, "/temp"));
		//必须要配置以下两项
		multipartResolver.setMaxInMemorySize(30 * 1024 * 1024);
		multipartResolver.setMaxUploadSizePerFile(3 * 1024 * 1024);
		multipartResolver.setMaxUploadSize(30 * 1024 * 102);
		return multipartResolver;
	}

	///**
	// * json信息转换器的bean。（需要有mapping）
	// */
	//@Bean
	//public HttpMessageConverter jsonHttpMessageConverter(){
	//	var converter = new MappingJackson2HttpMessageConverter();
	//	return converter;
	//}


	/**
	 * 配置视图解析器。
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		var viewResolver = new InternalResourceViewResolver();
		registry.viewResolver(viewResolver());
	}

	/**
	 * 配置类型转化器和格式化器。
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new CustomDateConverter());
	}

	/**
	 * 配置校验器。
	 */
	@Override
	public Validator getValidator() {
		return validator();
	}

	/**
	 * 配置自定义异常处理器。
	 */
	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(handlerExceptionResolver());
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
		//可以配置多个
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
		//配置登录拦截器
		//registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/account/**");
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
	}

	///**
	// * 配置信息转换器。
	// */
	//@Override
	//public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	//	converters.add(jsonHttpMessageConverter());
	//}
}
