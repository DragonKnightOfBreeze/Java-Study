package com.windea.study.thymeleaf.demo.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页的控制器
 */
@Controller
public class IndexController {
	@RequestMapping({"/", "/index", "/home", "/welcome"})
	public String index() {
		return "/index.html";
	}

	@RequestMapping("/sample")
	public String showSample() {
		return "/samplePage.html";
	}
}
