package com.windea.study.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	/**
	 * 登录
	 */
	@RequestMapping("/login/signIn.action")
	public String signIn(HttpSession session, String username, String password) {
		session.setAttribute("username", username);
		System.out.println("登录用户！");
		return "redirect:/item/findAll.action";
	}

	/**
	 * 登出。
	 */
	@RequestMapping("/login/singOut.action")
	public String signOut(HttpSession session) {
		session.setAttribute("username", null);
		System.out.println("登出用户！");
		return "redirect:/index.jsp";
	}
}
