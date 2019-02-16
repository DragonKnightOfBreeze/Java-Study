package com.windea.study.springmvc.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception {
		//得到请求的url
		var url = request.getRequestURI();
		//判断是否是公开地址，实际使用时需要将公开地址写到配置文件中。
		//可以将这次判断转移到配置类中。
		if(url.startsWith("/account")) {
			var username = request.getSession().getAttribute("username");
			if(!StringUtils.isEmpty(username)) {
				return true;
			}
			//跳转到登录页面
			request.getRequestDispatcher("/WEB-INF/pages/login/loginPage.jsp").forward(request, response);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {

	}
}
