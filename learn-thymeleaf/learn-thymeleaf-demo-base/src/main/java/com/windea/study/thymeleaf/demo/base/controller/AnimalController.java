package com.windea.study.thymeleaf.demo.base.controller;

import com.windea.study.thymeleaf.demo.base.domain.Animal;
import com.windea.study.thymeleaf.demo.base.service.IAnimalService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AnimalController {
	private final Log log = LogFactory.getLog(getClass());

	private final IAnimalService animalService;

	@Autowired
	public AnimalController(IAnimalService animalService) {this.animalService = animalService;}


	/**
	 * 添加动物信息。
	 */
	@RequestMapping(path = "/animal", params = "operation=insert", method = RequestMethod.POST)
	public String insert(Animal animal) {
		animalService.insert(animal);
		log.info("添加动物信息。");
		return "redirect:/animal/list";
	}

	/**
	 * 添加动物信息。进行验证。
	 */
	@RequestMapping(path = "/animal", params = "operation=insertIfValid", method = RequestMethod.POST)
	public String insertIfValid(Model model, @Valid Animal animal, BindingResult result) {
		//检查是否有错误
		if(result.hasErrors()) {
			model.addAttribute("message", "出错了！");
		} else {
			model.addAttribute("message", "提交成功！");
		}
		//添加数据
		animalService.insert(animal);
		log.info("添加动物信息。进行验证。");
		return "redirect:/animal/list";
	}

	/**
	 * 批量添加动物信息。
	 */
	@RequestMapping(path = "/animal", params = "operation=batchInsert", method = RequestMethod.POST)
	public String batchInsert(Animal[] animals) {
		for(var animal : animals) {
			animalService.insert(animal);
		}
		log.info("批量添加动物信息。");
		return "redirect:/animal/list";
	}

	/**
	 * 更新动物信息。
	 */
	@RequestMapping(path = "/animal", params = "operation=updateById", method = RequestMethod.POST)
	public String updateById(Animal animal) {
		animalService.updateById(animal);
		log.info("更新用户信息。");
		return "redirect:/animal/list";
	}

	/**
	 * 批量更新用户信息。
	 */
	@RequestMapping(path = "/animal", params = "operation=batchUpdateById", method = RequestMethod.POST)
	public String batchUpdateById(Animal[] animals) {
		for(var animal : animals) {
			animalService.updateById(animal);
		}
		log.info("批量更新用户信息。");
		return "redirect:/animal/list";
	}

	/**
	 * 通过id删除用户信息。
	 */
	@RequestMapping(path = "/animal", params = "operation=deleteById", method = RequestMethod.GET)
	public String deleteById(Integer id) {
		animalService.deleteById(id);
		log.info("通过id删除用户信息。");
		return "redirect:/animal/list";
	}

	/**
	 * 通过id编辑用户信息。
	 */
	@RequestMapping(path = "/animal/{id}/edit", method = RequestMethod.GET)
	public String editById(Model model, @PathVariable Integer id) {
		var animal = animalService.findById(id);
		model.addAttribute("animal", animal);
		log.info("通过id编辑用户信息。");
		return "/animal/editInfoPage.html";
	}

	/**
	 * 通过id查找动物信息。
	 */
	@RequestMapping(path = "/animal/{id}", method = RequestMethod.GET)
	public String findById(Model model, @PathVariable Integer id) {
		var animal = animalService.findById(id);
		model.addAttribute("animal", animal);
		log.info("通过id查找动物信息。");
		return "/animal/infoPage.html";
	}


	/**
	 * 查找所有动物信息。
	 */
	@RequestMapping(path = {"/animal", "/animal/list"}, params = "!likeName", method = RequestMethod.GET)
	public String findAll(Model model) {
		var animalList = animalService.findAll();
		//NOTE 如果不加这一句，Thymeleaf会报错
		model.addAttribute("animal", new Animal());
		model.addAttribute("animalList", animalList);
		log.info("查找所有用户信息。");
		return "/animal/listPage.html";
	}

	/**
	 * 根据动物名字模糊查询动物信息。
	 */
	@RequestMapping(path = {"animal", "/animal/list"}, params = "likeName", method = RequestMethod.GET)
	public String findByNameLike(Model model, String likeName) {
		var animalList = animalService.findByNameLike(likeName);
		model.addAttribute("animal", new Animal());
		model.addAttribute("animalList", animalList);
		log.info("根据动物名字模糊查询动物信息。");
		return "/animal/listPage.html";
	}


}
