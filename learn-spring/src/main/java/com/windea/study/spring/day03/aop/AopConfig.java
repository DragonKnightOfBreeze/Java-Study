package com.windea.study.spring.day03.aop;

import org.springframework.context.annotation.*;

//定义为设置类，在里面编写bean工厂方法
@Configuration
//开启自动代理，这样就不需要编写同一包内的显式的bean工厂方法
@EnableAspectJAutoProxy
//默认扫描当前类所在的包
@ComponentScan
public class AopConfig {
	//@Bean
	//public AopAspect aopAspect(){
	//	return new AopAspect();
	//}
	//
	////定义为bean的工厂方法
	//@Bean
	////定义作用范围
	////@Scope("singleton")
	//public AopService aopService(){
	//	return new AopService();
	//}
}
