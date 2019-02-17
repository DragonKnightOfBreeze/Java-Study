<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户信息列表</title>
</head>
<body>
	<h1>用户信息列表</h1>
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
		<%--@elvariable id="userList" type="java.util.List<com.windea.study.springmvc.main.domain.UserEx>"--%>
		<c:forEach var="user" items="${userList}">
		<tr>
			<td>${user.id}</td>
			<td>${user.username}</td>
			<td>${user.sex}</td>
			<td>${user.address}</td>
			<td>${user.birthday}</td>
			<td>
				<c:url var="findByIdUrl" value="/user/findById.action">
					<c:param name="id" value="${user.id}"/>
					<c:param name="modify" value="false"/>
				</c:url>
				<c:url var="modifyByIdUrl" value="/user/findById.action">
					<c:param name="id" value="${user.id}"/>
					<c:param name="modify" value="true"/>
				</c:url>
				<c:url var="deleteByIdUrl" value="/user/deleteById.action">
					<c:param name="id" value="${user.id}"/>
				</c:url>
				<a href="${findByIdUrl}">详情</a>
				<a href="${modifyByIdUrl}">修改</a>
				<a href="${deleteByIdUrl}">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>

	<h1>添加用户</h1>
	<form action="<c:url value="/user/insert.action"/>" method="post">
		<table>
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
				<td><input type="text" disabled/></td>
				<td><input type="text" name="username"/></td>
				<td>
					<input type="radio" id="sex-0" name="sex" value="0" checked/>
					<label for="sex-0">男生</label>
					<input type="radio" id="sex-1" name="sex" value="1"/>
					<label for="sex-1">女生</label>
				</td>
				<td><input type="text" name="address"/></td>
				<td><input type="date" name="birthday"/></td>
				<td><input type="submit" value="添加"/></td>
			</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
