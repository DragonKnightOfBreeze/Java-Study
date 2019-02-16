package com.windea.study.springmvc.controller;

import com.windea.study.springmvc.domain.Item;
import com.windea.study.springmvc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * REST风格的网页和控制层设计
 */
@Controller
public class RestItemController {
	private final ItemService itemService;

	@Autowired
	public RestItemController(ItemService itemService) {this.itemService = itemService;}

	/**
	 * 查询商品信息，返回json数据。（REST风格的url）
	 */
	@RequestMapping("/rest/item/{id}")
	@ResponseBody
	public Item findById(@PathVariable Integer id) throws Exception {

		return itemService.findById(id);
	}
}
