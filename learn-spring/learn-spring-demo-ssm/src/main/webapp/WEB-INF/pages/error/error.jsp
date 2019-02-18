<%--@elvariable id="error" type="com.windea.study.spring.demo.ssm.exception.BaseException"--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>错误页面</title>
</head>
<body>
	<h1>${error.message}</h1>
	<pre>${error.printStackTrace()}</pre>
</body>
</html>
