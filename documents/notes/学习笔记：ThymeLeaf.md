# 参考链接

[Thymeleaf官方文档](https://www.thymeleaf.org/documentation.html)

[Thymeleaf教程 （一） 简介 - mygzs的专栏 - CSDN博客](https://blog.csdn.net/mygzs/article/details/52441078)

[Thymeleaf教程 | 易学教程](https://www.e-learn.cn/thymeleaf)

[【 分类 】- thymeleaf - 水手008的博客（随心而谈） - CSDN博客](https://blog.csdn.net/asd_op/article/category/6525212)

------

# Thymeleaf标准表达式语法

### 简介

大多数Thymeleaf属性允许将它们的值设置为或包含表达式，由于它们使用的方言，我们将其称为标准表达式。这些表达式可以有五种类型:

* `${...}` : 变量表达式。
* `*{...}` : 选择表达式。
* `#{...}` : 消息 (i18n) 表达式。
* `@{...}` : 链接 (URL) 表达式。
* `~{...}` : 片段表达式。

### 变量表达式

变量表达式是OGNL表达式 - 如果将*Thymeleaf* 与*Spring* - 集成在上下文变量上(也称为Spring术语中的模型属性)，则为*Spring EL*。
它们看起来像这样:`${session.user.name}`

它们作为属性值或作为它们的一部分，取决于属性:  
`<span th:text="${book.author.name}">`

上面的表达式与下面是相同的(在OGNL和SpringEL中):  
`((Book)context.getVariable("book")).getAuthor().getName()`

访问Spring的bean对象：  
`${@authService.getUserName()}`

#### 系统基本对象

OGNL有以下几种基本内置对象：

* `#ctx`：servletContext
* `#object.vars`：servletContext中的变量
* `#locale`：context locale
* `#httpServletRequest`：（仅在Web目录中）httpServletRequest
* `#httpSession`：（仅在web目录中）httpSession 

可以用如下方式引用：  
`locale country: <span th:text="${#locale.country}">US</span>.`

#### Thymeleaf提供的对象

除了这些基本的对象,Thymeleaf还为我们提供一套实用的对象。

* `#dates`：为java.util.Date对象提供工具方法,比如：格式化,提取年月日等.
* `#calendars`：类似于`#dates`，但是只针对java.util.Calendar对象.
* `#numbers`：为数值型对象提供工具方法。
* `#strings`：为String对象提供工具方法。如：contains, startsWith, prepending/appending等。
* `#objects`：为Object对象提供常用的工具方法。
* `#bools`：为boolean对象提供常用的工具方法。
* `#arrays`：为arrays对象提供常用的工具方法。
* `#lists`：为List对象提供常用的工具方法。
* `#sets`：为Set对象提供常用的工具方法。
* `#maps`：为Map对象提供常用的工具方法。
* `#aggregates`：为集合对象提供聚合函数。
* `#messages`：用于将外部信息包含进来，类似于`#{...}`语法。
* `#ids`：为可能需要循环的ID属性提供常用的工具方法。

### 选择表达式

选择表达式就像变量表达式一样，它们不是整个上下文变量映射上执行，而是在先前选择的对象。  
它们看起来像这样:`*{customer.name}`

它们所作用的对象由`th:object`属性指定:
```
<div th:object="${session.user}">
	<!--使用选择表达式：-->
	<p>姓名：<span th:text="*{name}">...</span></p>
	<!--上面这种方法等同于：-->
	<p>邮箱：<span th:text="${session.user.email}">...</span></p>
	<!--也可以使用#object来引用-->
	<p>地址：<span th:text="${#object.address}">...</span></p>
	
	<!--这些方法可以混合使用-->
	<!--如果没有对象被选中，那么#{}和*{}表达式的意义是相同的-->
</div>
```

### 消息表达式

消息表达式(通常称为文本外部化，国际化或i18n)允许从外部源(如:`.properties`)文件中检索特定于语言环境的消息，通过键来引用这引用消息。

在Spring应用程序中，它将自动与Spring的MessageSource机制集成。  
属性文件如下：
```
main.title = Hello world!
main.helloTo = Hello {0}!
```
表达式如下：
```
#{main.title}
#{mian.helloTo(${name})}
```

以下是在模板中使用它们的方式:
```
<table>
  ...
  <th th:text="#{header.address.city}">...</th>
  <th th:text="#{header.address.country}">...</th>
  ...
</table>
```

如果希望消息键由上下文变量的值确定，或者希望将变量指定为参数，则可以在消息表达式中使用变量表达式:
`#{${config.adminWelcomeKey}(${session.user.name})}`

### 链接表达式

链接表达式在构建URL并向其添加有用的上下文和会话信息(通常称为URL重写的过程)。
因此，对于部署在Web服务器的`/myapp`上下文中的Web应用程序，可以使用以下表达式:
`<a th:href="@{/order/list}">...</a>`

可以转换成如下的东西:
`<a href="/myapp/order/list">...</a>`

甚至，如果需要保持会话，并且cookie未启用(或者服务器还不知道)，那么生成的格式为:
`<a href="/myapp/order/list;jsessionid=s2ds3fa31abd241e2a01932">...</a>`

网址也可以带参数，如下所示:
`<a th:href="@{/order/details(update)}">...</a>`
  
也可以这样：  
`<a th:href="@{/order/details(id=${orderId},type=${orderType})}">...</a>`

也可以通过`{paramName}`的格式来引用要传递到controller中的参数，即是后面括号中的参数，类似@PathVariable：
`<a th:href="@{/order/{id}/details(id=${orderId},type=${orderType})}">...</a>`

这将产生类似以下的结果：
（注意＆符号会在标签属性中进行HTML转义...）
`<a href="/myapp/order/details?id=23&type=online">...</a>`

链接表达式可以是相对的，在这种情况下，应用程序上下文将不会被加到URL的前面:
`<a th:href="@{../documents/report}">...</a>`

也是服务器相对的(同样，没有应用程序上下文的前缀):
`<a th:href="@{~/contents/main}">...</a>`

和协议相关(就像绝对URL一样，但浏览器将使用与正在显示的页面相同的HTTP或HTTPS协议):
`<a th:href="@{//static.mycompany.com/res/initial}">...</a>`

当然，链接表达式也可以是绝对的:
`<a th:href="@{http://www.mycompany.com/main}">...</a>`

但是绝对(或协议相对)URL ，在Thymeleaf链接表达式中应该添加什么值？ 很简单:由响应过滤器定义URL重写:在基于Servlet的Web应用程序中，对于每个输出的URL(上下文相对，相对，绝对…)，在显示URL之前，Thymeleaf总是调用`HttpServletResponse.encodeUrl(...)`机制。 这意味着一个过滤器可以通过包装HttpServletResponse对象来为应用程序执行自定义的URL重写。

### 片段表达式（？）

片段表达式用来表示标记的片段，并将其移动到模板中。  
由于这些表达式，片段可以被复制，传递给其他模板的参数等等。

最常见的是使用`th:insert`或`th:replace`来插入片段:
`<div th:insert="~{commons :: main}">...</div>`

但是它们可以在任何地方使用，就像任何其他变量一样:
```
<div th:with="frag=~{footer :: #main/text()}">
  <p th:insert="${frag}">
</div>
```

* `templateName::domSelector`、`templateName::[domSelector]`：引入模板页面中的某个模块。
* `templateName`：引入模板页面。
* `domSelector`、`this::domSelector`引入自身模板的模块。 
* templateName和domSelect都支持表达式语法。

* `th:include`：引入子模块的子元素，依然保留父模块的tag。 
* `th:replace`：引入子模块的所有元素，不保留父模块的tag。

片段表达式可以有参数。

### 基本的文字和操作

有很多类型的文字和操作可用，它们分别如下:

* 文字
	* 文本文字，例如:`'one text'`, `'Another one!'`,`…`
	* 数字文字，例如:`0`,`10`, `314`, `31.01`, `112.83`,`…`
	* 布尔文字，例如:`true`,`false`
	* Null文字，例如:`null`
	* 文字标记，例如:`one`, `sometext`, `main`,`…`
* 文本操作:
	* 字符串连接:`+`
	* 文字替换:`|The name is ${name}|`
* 算术运算:
	* 二进制操作:`+`, `-`, `*`, `/`, `%`
	* 减号(一元运算符):`-`
* 布尔运算:
	* 二进制运算符，`and`,`or`
	* 布尔否定(一元运算符):`!`,`not`
* 比较和相等:
	* 比较运算符:`>`,`<`,`>=`,`<=`(`gt`,`lt`,`ge`,`le`)
	* 相等运算符:`==`, `!=` (`eq`, `ne`)
* 条件操作符:
	* If-then:`(if) ? (then)`
	* If-then-else:`(if) ? (then) : (else)`
	* Default: `(value) ?: (defaultvalue)`

### 文本操作

文字间连接（使用单引号）：
`th:text="'The name of the user is ' + ${user.name}"`

模版字符串（可以在字符串中插入变量）：  
`<span th:text="|Welcome to our application, ${user.name}!|">`  
`<span th:text="${onevar} + ' ' + |${twovar}, ${threevar}|">`

### 条件表达式

可以这样用：
```
<tr th:class="${row.even}? 'even' : 'odd'">
	...
</tr>
```

也可以这样用：
```
<tr th:class="${row.even}? (${row.first}? 'first' : 'even') : 'odd'">
	...
</tr>
```

### 默认表达式

默认表达式可以简化表达式，但是阅读性差。例如：
```
<div th:object="${session.user}">
	...
	<p>Age: <span th:text="*{age}?: '(no age specified)'">27</span>.</p>
</div>
```

age变量如果是null的话，则执行`no age specified`这一段表达式。否则直接显示age变量。

### 表达式预处理

关于表达式的最后一件事是知道表达式预处理，在`__`之间指定，如下所示:
`#{selection.__${sel.code}__}`

上面代码中，第一个被执行的变量表达式是:`${sel.code}`，并且将使用它的结果作为表达式的一部分(假设`${sel.code}`的结果为:`ALL`)，在此处执行国际化的情况下(这将查找与关键`selection.ALL`消息)。



