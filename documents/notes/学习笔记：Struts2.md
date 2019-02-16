# 前置

------

# 第一天 Struts2入门

## Struts2概述

* web层常见框架
	* struts2
	* springMVC

## Struts2框架入门

1. 导入jar包
	* 不需要也不能全部导入
2. 创建action
	* 每次访问servlet时，都会默认执行service方法
	* 要创建servlet，新建类继承HttpServlet，重写类中的方法
	* 每次访问action时，默认执行execute方法
	* 要创建action，新建类继承
3. 配置action类的访问路径
	*. 创建struts2核心配置文件
		* 名称和位置是固定的，为`src/struts.xml`
		* 需要引入dtd文件
4. 配置Struts2过滤器
	* 参照示例Web项目中的web.xml文件		

## Struts2底层执行过程

1. 过滤器
	* 使用过滤器实现 
2. 第一步
	* 获取请求内容
	* 得到路径里面的hello的值
3. 第二步
	* 到src下面查找struts.xml，使用dom4j解析得到xml文件中内容
	* 根据hello的值到xml文件中找action标签，匹配name属性值是否一样
4. 第三步
	* 匹配name属性值一样，都是hello的类
	* 找到name属性所在的action标签里面的另外一个属性的class值
	* 得到值是action全路径
	* 使用反射实现功能
	
> NOTE 最好记住Struts过滤器的名字，面试可能会问
> `org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter
> dispatcher包后面接着的也可能是ng包

1. 过滤器在服务器启动时创建，并执行init方法
	* 在init方法中主要就是加载配置文件
	* 包含自带的和自己需要配置的文件
	* 需要关心的配置文件：`web.xml`,`struts.xml`
	
## Struts2相关配置

**Struts2的核心配置文件**

1. 名称和位置是固定的
2. 在配置文件中主要有三个标签：package、action、result

1. package标签
	* 说明
		* 类似于jar包，区别不同年代action
		* 在package标签里面才能配置action
	* name标签
		* 跟功能本身没有关系，在一个配置文件中可以写多个package标签
	* extends标签
		* 属性值是固定的，为struts-default
		* 写了这个属性后，在package里面配置的类就具有action的功能
	* namespace标签
		* namespace属性值和action标签的name属性值构成访问路径
		* 以/开头的相对路径，默认值：/
2. action标签
	* 说明
		* 用于配置Action类及其访问路径
	* name属性
		* namespace属性值和action标签的name属性值构成访问路径
		* 在一个package标签里面可写多个action标签，name属性值不能相同
	* class属性
		* action对应的Java类的全路径
	* method属性
		* 要让action里面的多个方法执行，可使用这个属性
		* 默认执行execute方法
3. result标签
	* 说明
		* 根据action的方法返回值，配置到不同的路径中去（包括其他的action）
	* name属性
		* 和方法的返回值对应
	* type属性
		* 配置如何到路径中去（转发或者重定向），默认值：转发
	* 文本
		* 页面路径，以/开头的相对路径

```xml
<struts>
    <package name="hellodemo" extends="struts-default" namespace="/">
        <action name="hello" class="day01.action.HelloAction">
            <result name="ok">/day01/hello.jsp</result>
        </action>
    </package>
</struts>
```

**Struts2常量配置**

1. 修改struts默认常量值
	* **常用方式：在`struts.xml`中进行配置**
	* 在src下面创建struts.properties文件，然后进行配置
	* 在web.xml中进行配置
2. constant标签
	* name属性：常量名，类似xxx.xxx.xxx的形式
	* value属性：常量值
3. 一些常用的常量
	* `struts.i18n.encoding=UTF-8`
	* 表单通过post方法提交数据到action里面，默认编码为UTF-8，可以解决乱码问题
	* 通过get方法提交，还需要进行转码处理
	
**分模块开发**

1. 可以单独写一个配置文件，然后引入到核心配置文件里面来
	* `<include file="day01/action/hello.xml"/>`
	* 注意：需要在同一个File Set中，并且这个File Set需要包含`struts-default.xml`

## Struts2的Action的创建

**Action表写方式**

1. 创建普通类，这个类不继承任何类，也不实现任何接口
2. 创建类，实现Action接口（用得不是很多）
3. 创建类，继承ActionSupport类（一般都用这种方式）

## Struts2的Action方法访问

**访问action的方法（重点）**

1. 有三种方式实现
	* 使用action标签的method属性，写上要执行的方法名
	* 使用通配符的方式实现
	* 通过动态访问实现（一般不用）
2. 要注意的几点
	* 如果action方法有返回值，在配置文件中没有配置，则会出现404错误
	* action的方法可以有String类型的返回值，也可以没有返回值
	* 如果action方法没有返回值，则不需要配置
	* 建议用返回`"none"`（也就是`NONE`）替代返回`null`

**如何实现**

1. 使用action标签的method属性实现
	* 缺陷：action中的每个方法都要进行配置
2. 使用通配符实现（重点）
	* 在name属性中用`*`匹配任意数量的字符
	* 在method属性中用`{1}`表示匹配的第一组字符
3. 使用动态访问实现
	* 首先要设置`struts.enable.DynamicMethodInvocation`常量的值为true
	* 在写访问路径时，在action名字后面加上`!(方法名)`

## 拓展 使用注解配置Action

> 需要导入`struts2-convention-plugin.jar`包
> 外部依赖：ASM（maven：`org.ow2.asm/asm/7.0`）
> 同样需要配置全局Struts过滤器

* `@ParentPackage`
	* 说明：指定父包。用于Action类。
	* value字段：包名。如果不加上这个注释，则为`struts-default`。
* `@Namespace`、`@Namespaces`
    * 说明：指定命名空间。用于Action类或方法。
    * value字段：命名空间名。如果不加上这个注释，则为`/`。
* `@Action`、`@Actions`
	* 说明：指定Action的访问URL。
	* value字段：映射路径名。
    * results字段：对应的`@Result`列表。
    * interceptorRefs字段：对应的`@InterceptorRef`列表。
    * params字段：参数列表。
    * exceptionMappings字段：对应的`@ExceptionMapping`列表。
* `@ResultPath`
	* 说明：指定结果页面的基路径。
	* value参数：结果的根路径。
* `@Result`、`@Results`
	* 说明：提供了Action结果的映射。（一个结果的映射）
	* name参数：和Action方法的返回值对应。可以有多个不同的值。
	* location参数：要跳转到的页面路径，以/开头的相对路径。
	* params字段：参数列表。
* `@InterceptorRef`、`@InterceptorRefs`
	* 说明：拦截器引用。
	* value参数：拦截器或者拦截器堆栈的名字。
	* params参数：参数列表。
* `@ExceptionMapping`、`@ExceptionMappings`
	* 说明：指定异常映射，映射一个声明异常。
	* result参数：对应的`@Result`。
	* exception参数：对应的异常类的全路径。
	* params参数：参数列表。

------

# 第二天 Struts2数据操作

## 结果页面配置

**全局结果页面**

1. result标签（或者@Result注解）配置action方法的返回值到不同的页面
2. 创建两个action，执行默认方法，返回success，跳转到同一个页面
	* 如果多个action，方法里面返回值是相同的，转到的页面也是相同的，这个时候可以使用全局结果页面配置
	* 配置方法1：在package标签里面的global-results标签里面配置，配置的是result标签
	* 只适用于这个package里面的action
	* 配置方法2：在action类上使用`@Action`注解（有局限性）

**局部结果配置**

1. 如果同时配置了全局页面和局部页面，以局部配置为准

**Result标签的type属性**

1. type属性
	* 说明：如何跳转到路径里面（转发还是重定向）
	* 默认值：dispatcher，转发
	* 可选值：redirect，重定向
	* 上面两个值是到页面中的配置，但也可以配置到其他action里面去
	* 可选值：chain，转发到action，一般不用，有缓存问题。
	* 可选值：redirectAction，重定向到action。路径替换成action的名字
	* 路径替换成action的名字，可加可不加`.action`后缀
	
## 在action获取表单提交数据

1. 在web阶段，提交表单到servlet里面，使用request对象的相关方法获取
2. 在struts中，提交表单到action，但是action没有request对象，不能直接使用
3. action中获取表单提交数据，主要有三种方式
	* 使用ActionContext类
	* 使用ServletActionContext类
	* 使用接口注入方式（一般不用）

**使用ActionContext类获取**

|方法           |说明             |
| -------------| ---------------|
|`void put(String key,Object value)`|对应`req.setAttribute()`方法|
|`Object get(String key)`|对应`req.getAttribute()`方法|
|`Map<String,Object> getSession()`|返回一个Session级的Map对象|
|`Map<String,Object> getApplication()`|返回一个Application级的Map对象|
|`static ActionContext getContext()`|获取当前线程的ActionContext对象|
|`Map<String,Parameter> getParameters()`|返回一个包含所有参数列表的Map|
|`void setApplication(Map<String,Parameter> application)`|设置Application的Attribute|
|`void setSession(Map<String,Parameter> session)`|设置Session的Attribute|

1. 具体演示
	* 创建表单，提交到action里面
	* 在action里面使用ActionContext获取数据

**使用ServletActionContext类获取**

|方法        |说明         |
|static HttpServletRequest getRequest()| |
|static HttpServletResponse getResponse()| |
|static ServletContext getServletContext()| |
|static PageContext getPageContext()| |

**使用接口注入方式获取**

1. 让action类实现一个接口
2. 相关接口包括
	* ServletRequestAware
	* ServletResponseAware
	* SessionAware
	* ServletContextAware

**在action操作预对象**

## Struts2提供获取表单数据的方式

**使用原始方式封装数据到实体类对象**

直接使用`req.getParameter()`方法得到表单数据，然后直接封装实体类对象。

越是最原始的方式，功能越强大，也最有效。

**属性封装**

在Struts2中，可以直接在Action中定义各种Java基本数据类型的字段，使这些字段与表单数据相对应，并利用这些字段进行数据传递。

1. 直接把表单提交数据封装到action的属性里面。
2. 实现步骤
	* 在action成员变量位置定义变量
	* 变量名称和表单输入项的name属性值一样
	* 生成变量的set方法（set和get方法）

**模型驱动封装（重点）**

1. 使用模型驱动方式，可以直接把表单数据封装到实体类对象里面
2. 实现步骤
	* 让action实现ModelDriven接口
	* 在action创建实体类对象
	* 实现接口里面的getModel方法，返回实体类对象
3. 需要注意的问题
	* 在一个action里面不能同时使用属性封装和模型驱动封装，获取同一个表单数据
	* 如果同时使用，只会执行模型驱动封装

**表达式封装（会用）**

1. 实现过程
	* 在action里面声明一个实体类
	* 生成实体类变量的set和get方法
	* 在表单输入项的name属性值里面写表达式的形式

**表达式封装和模型驱动的比较**

1. 相同点
	* 都可以把数据封装到实体类对象里面去
2. 不同点
	* 使用模型驱动只能把数据封装到一个实体类对象里面
	* 使用表达式封装可以把数据封装到不同的实体类对象里面

## Struts2获取数据封装到集合中（会用）

**封装到List集合**

1. action中的代码类似普通的封装
	* 在action中声明List<T>类型的对象
	* 生成对应的set和get方法
2. 在页面表单中name属性的值要写成表达式的形式
	* 例如：`name="list[0].name"`

**封装到Map集合**

1. action中的代码类似普通的封装
	* 在action中声明Map<T>类型的对象
    * 生成对应的set和get方法
2. 在页面表单中name属性的值要写成表达式的形式
	* 例如：`name="map['one'].name"`

------

# 第三天 Struts2值栈

## ognl概述

1. 之前web结果，学习过EL表达式，EL表达式用于在jsp中获取域对象里面的值
2. ognl也是一种表达式语言，功能更加强大
	* 主要用途：在Struts2里面操作值栈数据
	* 一般和struts2标签一起使用
3. ognl不是struts2的一部分，可以单独使用
	* 使用ognl时首先要导入jar包，已包含在struts2框架的jar包中

**ognl的特点**

* 支持对象方法调用。例如：`objName.methodName()`
* 支持类静态方法调用和值访问。例如：`@java.lang.String@format('foo%s','bar')`
* 支持赋值操作和表达式串联。例如：`price=100`
* 访问ognl上下文和ActionContext
* 操作集合对象

* 三个部分
	* 表达式Expression
	* 根对象Root Object
	* 上下文环境Context

## ognl入门案例

1. 使用ognl+struts2标签计算字符串长度
2. 使用struts2标签

## 什么是值栈


1. 之前servlet中的attribute就是一种域对象
2. 在Struts2里面提供了一种存储机制，就是值栈，类似于域对象，可以存值和取值
	* 在action里面把数据放到值栈里面，在页面中可以取到值栈数据
3. servlet和action的区别
	* Servlet：默认在第一次访问时创建，只会创建一次（单实例）
	* Action：访问时创建，每次访问时都会创建一次，创建一次（多实例）
4. 值栈存储位置
	* 每次访问action的时候，都会创建action对象
	* 在每个action对象里面，都会有一个值栈对象

## 获取值栈对象

1. 获取值栈有多种方式
	* 使用ActionContext类里面的方法获取
	* 代码：`ActionContext.getContext().getValueStack()`

**值栈内部结构**

1. 第一部分：root
	* 结构是list集合
	* 一般操作的都是root里面的数据
2. 第二部分：context
	* 结构是map结合
	* key：request，session，application，parameters，attr
	* value：对应对象的引用
	* parameters键：值为传递的相关参数
	* attr键：获取域范围最小的域对象里面的值
* 使用`s:debug`标签
	* 通过action到达jsp页面中
	* 点击以看到里面的结构
	* 如果action里面没有进行任何操作，，则栈顶元素为该action对象的引用
	
## 向值栈中存放数据

1. 存在多种方式
	* 获取值栈对象，调用set方法
	* 获取值栈对象，调用push方法
	* 在action里面定义变量，生成对应的get方法（最常用），在具体的方法里面设置值

1. 实现步骤
	* 定义对象变量
	* 生成变量的get方法
	* 在执行的方法里设置对象的值

## 从值栈中获取数据

1. 使用Struts2标签和ognl表达式获取数据
	* 使用`s:property`标签

1. 获取字符串
	* 代码：`<s:property value="userName"/>`
	* 得到对应action的userName成员变量的值
2. 获取实体对象
	* 代码：`<s:property value="user.userName"/>`
	* 得到对应action的user成员变量的userName属性的值
3. 获取list集合
	* 第一种方式：`<s:property value="list[0].userName"/>`
	* 第二种方式：使用`s:iterator`标签，遍历值栈中的集合
	* 第三种方式：同样使用`s:iterator`标签，同时使用var和value属性
		* 会把每次遍历出来的user对象放到context里面
		* 要取context里面的数据，需要在前面加上`#`

```
<s:iterator value="list">
	<s:property value="userName"/>
</s:iterator>
```

```
<s:iterator var="user" value="list">
	<s:property value="#user.userName"/>
</s:iterator>
```

## 其他操作

1. 取得使用set方法存放的数据
	* 代码：`<s:property value="userName"/>`
2. 取得使用push方法存放的数据
	* 不能根据名称取得
	* 向值栈中放数据，都会放到一个名称为top的数组中去
	* 代码：`<s:property value="[0].top"/>`

也可以通过`c:forEach`标签加上EL表达式取得list集合数据。    
EL表达式也可以获取值栈中的数据。

1. EL表达式用于获取域对象值
2. struts2增强了`setAttribute()`和`getAttribute()`方法
	* 首先尝试从request域中取值
	* 如果取不到，则尝试从值栈中取到，然后放到域对象中
3. 不建议用EL表达式取值，性能很低

## ognl中特殊符号的使用

**`#`**

1. 指明获取context里面的数据
	* 示例：`#user.userName`
	* 也可以使用EL表达式
	
**`%`**

1. 指明一个属性的值为ognl表达式的形式
2. 使用struts2的表单标签
	* 在struts2表单标签里面使用ognl表达式，如果直接使用不能识别
	* 只有写了`%`之后才能识别（`%{ognl表达式}`）
	* 示例：`<s:textfield name="userName" value="%{#user.userName}"></s:textfield>`
	* 表单标签性能很低，一般不用

**`@`**

1. 指明java类名全路径
2. 调用静态方法（替代`.`）
3. 还需要在核心配置文件中进行配置 
	* `<constant name="struts.ognl.allowStaticMethodAccess" value="true" />`

------

# 第四天 Struts2拦截器

（略）

# 拓展 struts2标签库

## 普通标签

* `s:property`
	* 说明：定义一个属性。
	* value属性：一个ognl表达式。
* `s:debug`
	* 说明：用于调试，不应该暴露给用户。可以查看值栈的结构和里面存储的值。
	
## 表单标签

# 拓展 ognl

## ActionContext结构

* ActionContext
	* ValueStack
		* Action
		* 其他
	* StackContext
		* Session
		* Request
		* Application
		* Parameters
		* Attribute
		* 其他

## 数据访问

* 访问上下文（ActionContext）中的属性
	* 访问ValueStack中的数据（一般都是Action里面的数据）
		* 不需要使用任何特殊的标记。
		* 示例：`<s:property value="userName"/>`
		* 说明：在ValueStack中查找名为userName的项，然后输出。
	* 访问StackContext中的数据
		* 需要以`#`开始。相当于`ActionContext.getContext()`。
		* 示例：`<s:property value="#userName"/>`
		* 说明：在StackContext中查找名为uid的项，然后输出。
		* 示例：`<s:property value="#session.userName"/>`
		* 说明：在StackContext的Session作用域中查找名为uid的项，然后输出。
* 访问实体类
	* 访问Action中的属性（包括实体类属性）
		* 需要在对应的Action中定义对应的属性，至少需要对应的get方法。
		* 示例：`<s:property value="userName""/>`
		* 示例：`<s:property value="user.userName""/>`
	* 调用实体类对象的非静态方法  
    	* 示例：`<s:property value="#request.user.sayHello('tom')"/>`  
	* 调用实体类的静态方法
		* 示例：`<s:property value="@User@sayBye('jerry')"/>`

## 语法

* 运算符
	* `+`、`—`、`*`、`/`、`==`、`!=`、`=`、`mod`、`in`、`not in`
* `%{}`符号
	* 说明：用于括起字符串，将其作为ognl表达式进行计算。
* 集合定义
	* 定义List集合
		* 示例：`{e1,e2,e3,...}`
	* 定义Map集合
		* 示例：`#{k1:v1,k2:v2,...}`
		* 示例：`#@java.util.LinkedHashMap@{ "foo" : "foo value", "bar" : "bar value" }`
* 集合过滤
	* `?`符号
		* 说明：取出所有符合选择逻辑的元素。
		* 示例：`#session.StudentList.{?#this.age>20}` 
    * `^`符号
    	* 说明：取出符合选择逻辑的第一个元素。
    	* 示例：`#session.StudentList.{^#this.age>20}`
    * `$`操作符
    	* 说明：取出符合选择逻辑的最后一个元素。
    	* 示例：`#session.StudentList.{$#this.age>20}`
    * 投影操作
    	* 说明：取出集合中每个元素的一个属性，组成新的集合。
    	* 示例：`user.{userName}`
* `$`符号   
	* 在国际化资源文件中，引用OGNL表达式  
	* 在Struts 2配置文件中，引用OGNL表达式
		* `<result type="redirect">listUser.action?msg=${msg}</result>`
* 特别注意
	* 在ognl中定义字符串时，需要用单引号括起。
	
# 拓展 其他部分

* 国际化
	* 在`struts.xml`中指定资源文件。
		* 示例：`<constant name="struts.custom.i18n.resources"value="properties/message"/>`
	* 编写多个语言的资源文件。
	* 在Action中：
		* 使用ActionSupport类的getText()方法访问国际化信息。
	* 在Jsp中 ：
		* 使用`s:text`标签访问国际化信息。
		* 示例：`<s:text name="loginPage"/>`
		* 使用内嵌的`s:param`标签设置占位符参数。


