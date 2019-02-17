package com.windea.study.springmvc.main.controller;

import com.windea.study.springmvc.main.domain.Item;
import com.windea.study.springmvc.main.domain.ItemQueryVo;
import com.windea.study.springmvc.main.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController3 {
	private final ItemService service;

	@Autowired
	public ItemController3(ItemService service) {this.service = service;}

	@RequestMapping("/item/insert.action")
	public String insert(Item item) throws Exception {
		service.insert(item);
		return "redirect:/item/findAll.action";
	}

	@RequestMapping("/item/updateById.action")
	public String updateById(Integer id, Item item) throws Exception {
		service.updateById(id, item);
		return "redirect:/item/findAll.action";
	}

	@RequestMapping("/item/deleteById.action")
	public String deleteById(Integer id) throws Exception {
		service.deleteById(id);
		return "redirect:/item/findAll.action";
	}

	@RequestMapping("/item/findById.action")
	public String findById(Model model, Integer id) throws Exception {
		var item = service.findById(id);
		model.addAttribute("item", item);
		return "/item/itemInfo.jsp";
	}

	@RequestMapping("/item/findAll.action")
	public String findAll(Model model) throws Exception {
		var itemList = service.findAll();
		model.addAttribute("itemList", itemList);
		return "/item/itemList.jsp";
	}

	@RequestMapping("/item/findByConditions.action")
	public String findByConditions(Model model, ItemQueryVo queryVo) throws Exception {
		var itemList = service.findByConditions(queryVo);
		model.addAttribute("itemList", itemList);
		return "/item/itemList.jsp";
	}

	/**
	 * 批量删除操作
	 */
	@RequestMapping("/item/deleteByIdBatch.action")
	public String deleteByIdBatch(Integer[] ids) throws Exception {
		for(var id : ids) {
			service.deleteById(id);
		}
		return "redirect:/item/findAll.action";
	}

	/**
	 * 批量修改操作
	 * <p>将商品信息存储到queryVo的itemList属性中。
	 */
	@RequestMapping("/item/updateByIdBatch.action")
	public String updateByIdBatch(ItemQueryVo queryVo) throws Exception {
		var itemList = queryVo.getItemList();
		for(var item : itemList) {
			service.updateById(item.getId(), item);
		}
		return "redirect:/item/findAll.action";
	}

	/**
	 * 批量编辑操作。
	 */
	@RequestMapping("/item/editByIdBatch.action")
	public String editByIdBatch(Model model, Integer[] ids) throws Exception {
		List<Item> itemList = new ArrayList<>();
		for(var id : ids) {
			var item = service.findById(id);
			itemList.add(item);
		}
		model.addAttribute("itemList", itemList);
		return "/item/editItemList.jsp";
	}

	/**
	 * 查询全部并分页显示。
	 */
	@RequestMapping("/item/findAllWithPage.action")
	public String findAllWithPage(Model model) throws Exception {
		var itemPage = service.findAllWithPage(1, 10);
		model.addAttribute("itemPage", itemPage).addAttribute("itemList", itemPage.getList());
		return "/item/itemList.jsp";
	}
}
