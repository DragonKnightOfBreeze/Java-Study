## .开发您的第一个Spring Statemachine应用程序

让我们从创建一个简单的Spring Boot `Application`类开始`CommandLineRunner`。

```java
@SpringBootApplication
public class Application implements CommandLineRunner {
    public  static  void main(String [] args) {
        SpringApplication.run(Application.class,args);
    }
}
```

添加状态和事件：

```java
public enum States{
    SI, S1, S2
}

public enum Events {
    E1, E2
}
```

添加状态机配置：

```java
@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
            .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
            .withStates()
                .initial(States.SI)
                    .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
            .withExternal()
                .source(States.SI).target(States.S1).event(Events.E1)
                .and()
            .withExternal()
                .source(States.S1).target(States.S2).event(Events.E2);
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}
```

实施`CommandLineRunner`，autowire `StateMachine`：

```
@Autowired
private StateMachine<States, Events> stateMachine;

@Override
public void run(String... args) throws Exception {
    stateMachine.sendEvent(Events.E1);
    stateMachine.sendEvent(Events.E2);
}
```

取决于您使用`Gradle`或`Maven` 运行`java -jar build/libs/gs-statemachine-0.1.0.jar`或 `java -jar target/gs-statemachine-0.1.0.jar`分别构建应用程序。

运行此命令的期望是普通的Spring Boot输出，但如果仔细观察，您会看到以下行：

```
State change to SI
State change to S1
State change to S2
```
