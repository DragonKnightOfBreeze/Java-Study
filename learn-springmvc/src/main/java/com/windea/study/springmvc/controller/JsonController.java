package com.windea.study.springmvc.controller;

import com.windea.study.springmvc.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class JsonController {
	@RequestMapping("/json/start.action")
	public String test() {
		return "/json/jsonPage.jsp";
	}

	/**
	 * 接收json对象，转化为java对象，返回json串。
	 */
	@RequestMapping("/json/requestJson.action")
	@ResponseBody
	public Item requestJson(@RequestBody Item item) {
		System.out.println(item);
		return item;
	}

	/**
	 * 接收json数组，转化为java数组/列表，返回json数组
	 */
	@RequestMapping("/json/requestJson2.action")
	@ResponseBody
	public List<String> requestJson2(@RequestBody List<String> list) {
		System.out.println(list);
		return list;
	}

	/**
	 * 接收json对象，转化为java图表，返回json对象。
	 */
	@RequestMapping("/json/requestJson3.action")
	@ResponseBody
	public Map<String, Object> requestJson3(@RequestBody Map<String, Object> map) {
		System.out.println(map);
		return map;
	}

	/**
	 * 接收kv，转化为java对象，返回json对象。
	 */
	@RequestMapping("/json/responseJson.action")
	@ResponseBody
	public Item responseJson(Item item) {
		System.out.println(item);
		return item;
	}
}
