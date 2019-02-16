package com.windea.study.spring.day03.aop;

import org.springframework.stereotype.Service;

@Service
public class AopService {
	public void print() {
		System.out.println("hello world!");
	}
}
