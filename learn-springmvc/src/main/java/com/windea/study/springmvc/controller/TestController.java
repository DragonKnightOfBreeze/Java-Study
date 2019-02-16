package com.windea.study.springmvc.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TestController {
	@GetMapping("/test/start.action")
	public String start() {
		return "/test/testPage.jsp";
	}

	//<input type="text" name="str"/>
	@RequestMapping("/test/stringTest.action")
	public String stringTest(@RequestParam("str") String str) {
		System.out.println(str);
		return "redirect:/test/start.action";
	}

	//<input type="text" name="str"/>
	@RequestMapping("/test/stringTest2.action")
	public String stringTest2(String str) {
		System.out.println(str);
		return "redirect:/test/start.action";
	}

	//<input type="text" name="name"/>
	@RequestMapping("/test/stringTest3.action")
	public String stringTest3(Bean bean) {
		System.out.println(bean.getName());
		return "redirect:/test/start.action";
	}

	//WARN 得不到
	//<input type="text" name="bean.name"/>
	@RequestMapping("/test/stringTest4.action")
	public String stringTest4(@Param("bean") Bean bean) {
		System.out.println(bean.getName());
		return "redirect:/test/start.action";
	}


	//<input type="text" name="strArray"/>
	//<input type="checkbox" name="strArray" value="1"/>
	@RequestMapping("/test/arrayTest.action")
	public String arrayTest(@RequestParam("strArray") String[] strArray) {
		System.out.println(Arrays.toString(strArray));
		return "redirect:/test/start.action";
	}

	//<input type="text" name="strArray"/>
	//<input type="checkbox" name="strArray" value="1"/>
	@RequestMapping("/test/arrayTest2.action")
	public String arrayTest2(String[] strArray) {
		System.out.println(Arrays.toString(strArray));
		return "redirect:/test/start.action";
	}


	//<input type="text" name="strList"/>
	//<input type="checkbox" name="strList" value="1"/>
	@RequestMapping("/test/listTest.action")
	public String listTest(@RequestParam("strList") List<String> strList) {
		System.out.println(strList);
		return "redirect:/test/start.action";
	}

	//WARN 不可行
	//<input type="text" name="strList"/>
	//<input type="checkbox" name="strList" value="1"/>
	@RequestMapping("/test/listTest2.action")
	public String listTest2(List<String> strList) {
		System.out.println(strList);
		return "redirect:/test/start.action";
	}

	//WARN ???
	//<input type="text" name="list[0]"/>
	@RequestMapping("/test/listTest3.action")
	public String listTest3(Bean bean) {
		System.out.println(bean.getList().get(0));
		return "redirect:/test/start.action";
	}

	//WARN ???
	//<input type="text" name="inList[0].str"/>
	@RequestMapping("/test/listTest4.action")
	public String listTest4(Bean bean) {
		System.out.println(bean.getInList().get(0).getStr());
		return "redirect:/test/start.action";
	}

	//WARN 不可行
	//<input type="text" name="strMap"/>
	//<input type="checkbox" name="strMap" value="1"/>
	@RequestMapping("/test/mapTest.action")
	public String mapTest(@RequestParam("strMap") Map<String, String> strMap) {
		System.out.println(strMap);
		return "redirect:/test/start.action";
	}
}

class Bean {
	private String name;
	private List<String> list;
	private List<In> inList;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<In> getInList() {
		return inList;
	}

	public void setInList(List<In> inList) {
		this.inList = inList;
	}
}

class In {
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
