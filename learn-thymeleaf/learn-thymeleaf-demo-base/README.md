# 参考

整理自：  
[【 分类 】- thymeleaf - 水手008的博客（随心而谈） - CSDN博客](https://blog.csdn.net/asd_op/article/category/6525212)

# 说明

* 包含参数验证的例子
* restful格式的链接

# 问题解决

## Thymeleaf乱码问题（页面显示和post参数传递）

* 在AppInitializer中：
	* 添加编码过滤器，设置编码为UTF-8，并且强制编码
* 在DispatcherServlet中：
	* 配置@Bean templateResolver的编码为UTF-8
	* 配置@Bean viewResolver的编码为UTF-8
* 在页面中：
	* 在head中加入以下标签
	* `<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">`
