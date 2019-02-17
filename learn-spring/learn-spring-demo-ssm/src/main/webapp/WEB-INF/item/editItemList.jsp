<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--@elvariable id="itemList" type="java.util.List<com.windea.study.springmvc.main.domain.Item>"--%>

<!DOCTYPE html>
<html>
<head>
	<title>批量编辑商品信息</title>
</head>
<body>
	<h1>商品列表</h1>

	<form id="batchForm" action="<c:url value="/item/updateByIdBatch.action"/>" method="post">
		<input type="submit" value="提交"/>

		<table width="100%" border="1">
			<thead>
			<tr>
				<td>商品id</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>商品图片地址</td>
				<td>商品创建时间</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${itemList}" varStatus="vs">
				<tr>
					<td>
						<input type="text" value="${item.id}" disabled/>
						<input type="hidden" name="itemList[${vs.index}].id" value="${item.id}"/>
					</td>
					<td>
						<input type="text" name="itemList[${vs.index}].name" value="${item.name}"/>
					</td>
					<td>
						<input type="number" name="itemList[${vs.index}].price" value="${item.price}"/>
					</td>
					<td>
						<input type="date" name="itemList[${vs.index}].createTime" value="${item.createTime}"/>
					</td>
					<td>
						<textarea name="itemList[${vs.index}].detail">${item.detail}</textarea>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>
