<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--@elvariable id="user" type="com.windea.study.springmvc.domain.User"--%>
<!DOCTYPE html>
<html>
<head>
	<title>用户信息</title>
</head>
<body>
	<h1>用户信息</h1>
	<table width="100%" border="1">
		<thead>
		<tr>
			<td>用户id</td>
			<td>用户名</td>
			<td>用户性别</td>
			<td>用户地址</td>
			<td>用户生日</td>
			<td>操作</td>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>${user.id}</td>
			<td>${user.username}</td>
			<td>${user.sex}</td>
			<td>${user.address}</td>
			<td>${user.birthday}</td>
			<td>&emsp;</td>
		</tr>
		</tbody>
	</table>
</body>
</html>
