<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--@elvariable id="user" type="com.windea.study.springmvc.domain.User"--%>

<!DOCTYPE html>
<html>
<head>
	<title>修改用户信息</title>
</head>
<body>
	<h1>修改用户信息</h1>
	<form action="<c:url value="/user/updateById.action"/>" method="post">
		<input type="hidden" name="id" value="${user.id}"/>
		<table>
			<tr>
				<td><label for="id">用户id</label></td>
				<td><input type="text" id="id" value="${user.id}" disabled/></td>
			</tr>
			<tr>
				<td><label for="username">用户名</label>
				<td><input type="text" id="username" name="username" value="${user.username}"/></td>
			</tr>
			<tr>
				<td>
					<input type="radio" id="sex-0" name="sex" value="0" ${user.sex == 0?'checked':''}/>
					<label for="sex-0">男生</label>
				</td>
				<td>
					<input type="radio" id="sex-1" name="sex" value="1" ${user.sex == 1?'checked':''}/>
					<label for="sex-1">女生</label>
				</td>
			</tr>
			<tr>
				<td><label for="address">用户地址</label>
				<td><input type="text" id="address" name="address" value="${user.address}"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交"/></td>
			</tr>
		</table>
	</form>
</body>
</html>

