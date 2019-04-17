package com.windea.study.springstatemachine.demo1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private final StateMachine<States, Events> stateMachine;

	public Application(StateMachine<States, Events> stateMachine) {this.stateMachine = stateMachine;}

	@Override
	public void run(String... args) throws Exception {
		stateMachine.sendEvent(Events.E1);
		stateMachine.sendEvent(Events.E2);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
