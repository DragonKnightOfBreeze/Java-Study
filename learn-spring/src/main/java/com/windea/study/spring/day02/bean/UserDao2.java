package com.windea.study.spring.day02.bean;

import org.springframework.stereotype.Repository;

@Repository("userDao2")
public class UserDao2 {
	public void print() {
		System.out.println("Hello world!");
	}
}
