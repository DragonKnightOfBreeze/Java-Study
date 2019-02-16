package com.windea.study.spring.day02.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("user")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class User {
	public void print() {
		System.out.println("Hello!");
	}
}
