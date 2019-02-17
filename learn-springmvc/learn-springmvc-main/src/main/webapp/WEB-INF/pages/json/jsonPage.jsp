<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<!DOCTYPE html>
<html>
<head>
	<title>Json交互测试</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
	<script>
		/**
		 * 请求json，响应json
		 * 设置contentType=application/json
		 * 在controller层中，使用@RequestBody获取json数据
		 */

		function requestJson() {
			$.post({
				url: "<c:url value="/json/requestJson.action"/>",
				//数据格式是json串
				data: {name: "Iphone", price: 123},
				success: data => alert(data),
				contentType: "application/json;charset=utf-8"
			});
		}

		function requestJson2() {
			$.post({
				url: "<c:url value="/json/requestJson2.action"/>",
				//数据格式是json串
				data: `["Iphone", "Android"]`,
				success: data => alert(data),
				contentType: "application/json;charset=utf-8"
			});
		}

		function requestJson3() {
			$.post({
				url: "<c:url value="/json/requestJson3.action"/>",
				//数据格式是json串
				data: `{"name": "Iphone", "price": 123}`,
				success: data => alert(data),
				contentType: "application/json;charset=utf-8"
			});
		}

		/**
		 * 请求kv，响应json
		 */
		function responseJson() {
			$.post({
				url: "<c:url value="/json/responseJson.action"/>",
				//数据格式是json串
				data: {name: "Iphone", price: "123"},
				success: data => alert(data)
			});
		}
	</script>
</head>
<body>
	<button onclick="requestJson()">请求json（对象）</button>
	<button onclick="requestJson2()">请求json（数组）</button>
	<button onclick="requestJson3()">请求json（映射）</button>
	<button onclick="responseJson()">响应json</button>
</body>
</html>
