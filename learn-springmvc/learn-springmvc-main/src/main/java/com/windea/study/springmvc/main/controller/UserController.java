package com.windea.study.springmvc.main.controller;

import com.windea.study.springmvc.main.domain.*;
import com.windea.study.springmvc.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService service;

	@Autowired
	public UserController(UserService service) {this.service = service;}

	@RequestMapping("/insert.action")
	public ModelAndView insert(User user) throws Exception {
		//var user = new User();
		//user.setUsername("Fubuki");
		//user.setBirthday(new Date());
		//user.setSex(1);
		//user.setAddress("不知道");

		service.insert(user);
		return new ModelAndView("redirect:/findAll.action");
	}

	@RequestMapping("/deleteById.action")
	public ModelAndView deleteById(@RequestParam Integer id) throws Exception {
		service.deleteById(id);
		return new ModelAndView("redirect:/findAll.action");
	}

	@RequestMapping("/updateById.action")
	public ModelAndView updateById(Integer id, User user) throws Exception {
		service.updateById(id, user);
		return new ModelAndView("redirect:/findAll.action");
	}

	@RequestMapping(value = "/findById.action", method = RequestMethod.POST)
	public ModelAndView findById(Integer id, boolean modify) throws Exception {
		var user = service.findById(id);
		var url = modify ? "/user/modifyUserInfo.jsp" : "/user/userInfo.jsp";
		return new ModelAndView("forward:" + url).
				addObject("user", user);
	}

	@RequestMapping("/findAll.action")
	public ModelAndView findAll() throws Exception {
		var userList = service.findAll();
		return new ModelAndView("forward:/user/userList.jsp")
				.addObject("userList", userList);
	}


	@RequestMapping("/findByUsernameLike.action")
	public ModelAndView findByUsernameLike() throws Exception {
		var userList = service.findByUsernameLike("Wind");
		return new ModelAndView("/user/userList.jsp").
				addObject("userList", userList);
	}

	@RequestMapping("/findByConditions.action")
	public ModelAndView findByConditions() throws Exception {
		var query = new UserQueryVo();
		var userEx = new UserEx();
		userEx.setUsername("Wind");
		userEx.setSex(1);
		query.setUserEx(userEx);

		var userList = service.findByConditions(query);
		return new ModelAndView("/user/userList.jsp").
				addObject("userList", userList);
	}
}
