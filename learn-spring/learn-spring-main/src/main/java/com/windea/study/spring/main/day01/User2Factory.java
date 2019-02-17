package com.windea.study.spring.main.day01;

//使用静态工厂创建bean对象
public class User2Factory {
	public static User2 getUser2() {
		return new User2();
	}
}

class User2 {
	public void add() {
		System.out.println("add...");
	}
}
