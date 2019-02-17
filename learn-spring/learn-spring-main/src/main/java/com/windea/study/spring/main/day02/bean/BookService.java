package com.windea.study.spring.main.day02.bean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("bookService")
public class BookService {
	@Resource(name = "bookDao")
	private BookDao bookDao;
	@Resource(name = "orderDao")
	private OrderDao orderDao;
}
