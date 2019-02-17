package com.windea.study.spring.demo.ssm.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {
	private Log log = LogFactory.getLog(getClass());

	//进入handler方法之前调用。
	//应用场景：身份认证、身份授权
	//返回true表示放行，返回false表示拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception {
		log.info("开始执行处理器。");
		return true;
	}

	//进入handler方法后，返回结果之前执行
	//应用场景：传递共用的模型数据到视图，也可以统一指定视图。
	//例如：菜单导航
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("处理器执行中。");
	}

	//handler完成时执行
	//应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		log.info("处理器执行结束。");
	}
}
