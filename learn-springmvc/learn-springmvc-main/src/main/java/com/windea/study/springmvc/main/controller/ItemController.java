package com.windea.study.springmvc.main.controller;

import com.windea.study.springmvc.main.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品的控制类
 * @noinspection Duplicates
 */
@Controller
public class ItemController implements org.springframework.web.servlet.mvc.Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
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
