<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
</head>
<body>
	<form action="<c:url value="/login/signIn.action"/>" method="post">
		<label for="username">用户名</label>
		<input id="username" type="text" name="username"/>
		<label for="password">密码</label>
		<input id="password" type="password" name="password"/>
		<input type="submit" value="登录"/>
	</form>

	<br>
	<hr>
	<br>
	<a href="<c:url value="/item/findAll.action"/>">查找所有商品</a>
	<hr>
	<a href="<c:url value="/test/start.action"/>">参数传递测试页面</a>
	<hr>
	<a href="<c:url value="/json/start.action"/>">Json测试页面</a>
	<br>
	<hr>
	<br>
	<form action="<c:url value="/io/upload.action"/>" method="post" enctype="multipart/form-data">
		<input type="file" name="image"/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>
