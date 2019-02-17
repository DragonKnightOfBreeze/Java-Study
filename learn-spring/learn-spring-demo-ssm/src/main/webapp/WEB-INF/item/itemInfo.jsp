<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--@elvariable id="item" type="com.windea.study.springmvc.main.domain.Item"--%>

<!DOCTYPE html>
<html>
<head>
	<title>商品信息</title>
</head>
<body>
	<h1>商品信息</h1>
	<table width="100%" border="1">
		<thead>
		<tr>
			<td>商品id</td>
			<td>商品名称</td>
			<td>商品价格</td>
			<td>商品图片地址</td>
			<td>商品详情</td>
			<td>商品创建时间</td>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>${item.id}</td>
			<td>${item.name}</td>
			<td>${item.price}</td>
			<td>${item.imageUrl}</td>
			<td>${item.detail}</td>
			<td>${item.createTime}</td>
			<td>
				<button onclick="history.back()">返回</button>
			</td>
		</tr>
		</tbody>
	</table>
</body>
</html>
