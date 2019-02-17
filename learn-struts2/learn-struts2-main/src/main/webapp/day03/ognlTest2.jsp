<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>测试表单</title>
</head>
<body>
	<s:property value="userName"/>

	<s:property value="user.userName/"/>


	<s:textfield name="userName" value="%{#user.userName}">123</s:textfield>
</body>
</html>

