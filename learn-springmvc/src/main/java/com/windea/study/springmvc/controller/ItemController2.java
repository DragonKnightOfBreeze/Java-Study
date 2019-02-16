package com.windea.study.springmvc.controller;

import com.windea.study.springmvc.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品的控制器（使用注解开发）
 * @noinspection Duplicates
 */
//表示这个类是一个控制器
@Controller
public class ItemController2 {

	//配置url，一般建议与方法名保持一致
	@RequestMapping("/item/findAll_D.action")
	public ModelAndView findAll() throws Exception {
		//调用service查询数据库，查询商品列表
		List<Item> itemList = new ArrayList<>();
		Item item1 = new Item();
		item1.setId(1);
		item1.setName("商品1");
		item1.setPrice(100.0);
		itemList.add(item1);
		Item item2 = new Item();
		item2.setId(2);
		item2.setName("商品2");
		item2.setPrice(500.0);
		itemList.add(item2);

		ModelAndView modelAndView = new ModelAndView();
		//相当于request的setAttribute方法，在jsp中通过itemList取得数据
		modelAndView.addObject("itemList", itemList);
		modelAndView.setViewName("/item/itemList.jsp");

		return modelAndView;
	}
}
