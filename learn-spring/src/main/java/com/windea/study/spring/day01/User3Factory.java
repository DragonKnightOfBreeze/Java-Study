package com.windea.study.spring.day01;

//使用静态工厂创建bean对象
public class User3Factory {
	public User3 getUser3() {
		return new User3();
	}
}

class User3 {
	public void add() {
		System.out.println("add...");
	}
}

