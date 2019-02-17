# 说明

整合SSH框架的web实例项目。  
尽可能地使用注解。

# 问题解决

## 项目结构问题

* 使用Maven时，源代码放在/src/main/java下，配置文件放在/src/main/resources下。

## 解决`struts-convention-plugin`包的相关异常：

* 不论是ClassLoader的异常，还是无法解析Action的异常。
* 当Java版本为11时，将`struts2-convention-plugin`包所依赖的`asm`包的版本改为最新的7.0，而非默认的5.2。
* 其他的asm组件没有必要更新版本。
* 非常非常坑。

## 消除Log4j的相关控制台警告：

* 需要导入`log4j-core`包，并且在根目录创建正确格式的`log4j2-test.xxx`配置文件，允许多种格式。

## 数据库连接问题

* 使用新版jdbc驱动`com.mysql.cj.jdbc.Driver`时，需要设置参数`serverTimezone=GMT%2B8`。
* `%2B`用于转义成`+`。
	
## Tomcat部署时间过长的问题

* 编辑$CATALINA_HOME/conf/logging.properties文件，在底部追加如下配置：
* `org.apache.jasper.compiler.TldLocationsCache.level = FINE`。
	
## Spring自动注入的问题

* `@Autowired`可以注解在字段上、set方法上、构造方法上，但是不推荐注解到字段上。
* `@Qualifier`、`@Resource`可以直接注解在字段上，但是必须写明属性值。

## Hibernate整合时报错

* 在Hibernate的核心配置文件中，注掉`<property name="hibernate.current_session_context_class">thread</property>`。
* 这在高版本中是不需要的。
