package com.windea.study.spring.demo.ssm.exception;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class AppHandlerExceptionResolver extends SimpleMappingExceptionResolver {
	/**
	 * * 解析出异常类型
	 * * 如果是自定义异常，则直接取出异常信息，在错误页面显示。
	 * * 如果不是自定义异常，则构造一个自定义异常（未知错误）。可能：加上错误代码。
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			@Nullable Object handler, Exception ex) {
		BaseException error;
		if(ex instanceof BaseException) {
			error = (BaseException) ex;
		} else {
			error = new BaseException("未知错误", ex);
		}
		error.printStackTrace();

		return new ModelAndView("/error/error.jsp").addObject("error", error);
	}

	/**
	 * 绑定异常类型和视图名。
	 */
	@Override
	public void setExceptionMappings(Properties mappings) {
		super.setExceptionMappings(mappings);
	}

	/**
	 * 绑定出错码和视图名。
	 */
	@Override
	public void setStatusCodes(Properties statusCodes) {
		super.setStatusCodes(statusCodes);
	}
}
