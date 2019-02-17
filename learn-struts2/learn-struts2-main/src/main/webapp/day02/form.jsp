<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>测试表单</title>
</head>
<body>
	<form action="<s:url namespace="/day02" action="form2"/>" method="post">
		<label for="userName">userName</label>
		<input type="text" id="userName" name="userName"/>
		<br/>
		<label for="password">password</label>
		<input type="password" id="password" name="password"/>
		<br/>
		<label for="address">address</label>
		<input type="text" id="address" name="address"/>
		<br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>
