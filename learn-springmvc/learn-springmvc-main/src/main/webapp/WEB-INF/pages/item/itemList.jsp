<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--@elvariable id="itemList" type="java.util.List<com.windea.study.springmvc.main.domain.Item>"--%>

<!DOCTYPE html>
<html>
<head>
	<title>查询商品列表</title>
	<script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
	<script>
		function doOperation(operation) {
			let url = null;
			switch(operation) {
				case "deleteByIdBatch":
					url = '<c:url value="/item/deleteByIdBatch.action"/>';
					break;
				case "updateByIdBatch":
					url = `<c:url value="/item/editByIdBatch.action"/>`;
					break;
				default:
					break;
			}
			$("#batchForm").prop("action", url).submit();
		}
	</script>
</head>
<body>
	<h1>商品列表</h1>
	<form action="<c:url value="/item/findByConditions.action"/> " method="post">
		<table>
			<tr>
				<td><label for="item.name">商品名称</label></td>
				<td><input type="text" id="item.name" name="item.name"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="查询"/></td>
			</tr>
		</table>
	</form>

	<hr>

	<form id="batchForm" action="" method="post">
		<input type="submit" value="批量删除" onclick="doOperation('deleteByIdBatch')"/>
		<input type="submit" value="批量更新" onclick="doOperation('editByIdBatch')"/>

		<table width="100%" border="1">
			<thead>
			<tr>
				<td>选择</td>
				<td>商品id</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>商品图片地址</td>
				<td>商品创建时间</td>
				<td>操作</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${itemList}">
				<tr>
					<td><input type="checkbox" name="ids" value="${item.id}"/></td>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.createTime}</td>
					<td>${item.detail}</td>
					<td>
						<a href="<c:url value="/item/findById.action?id=${item.id}"/>">详情</a>&emsp;
						<a href="<c:url value="/item/deleteById.action?id=${item.id}"/>">删除</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>
