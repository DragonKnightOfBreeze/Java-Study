<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>参数传递的测试页面</title>
</head>
<body>
	stringTest

	<form action="<c:url value="/test/stringTest.action"/>" method="post">
		<input type="text" name="str"/>
		<input type="submit" value="提交"/>
	</form>

	<form action="<c:url value="/test/stringTest2.action"/>" method="post">
		<input type="text" name="str"/>
		<input type="submit" value="提交"/>
	</form>

	<form action="<c:url value="/test/stringTest3.action"/>" method="post">
		<input type="text" name="name"/>
		<input type="submit" value="提交"/>
	</form>

	<form action="<c:url value="/test/stringTest4.action"/>" method="post">
		<input type="text" name="bean.name"/>
		<input type="submit" value="提交"/>
	</form>

	<hr>

	arrayTest

	<form action="<c:url value="/test/arrayTest.action"/>" method="post">
		<input type="text" name="strArray"/>
		<input type="text" name="strArray"/>
		<input type="text" name="strArray"/>

		<%--<input type="checkbox" name="strArray" value="1"/>--%>
		<%--<input type="checkbox" name="strArray" value="2"/>--%>
		<%--<input type="checkbox" name="strArray" value="3"/>--%>
		<input type="submit" value="提交"/>
	</form>

	<form action="<c:url value="/test/arrayTest2.action"/>" method="post">
		<input type="text" name="strArray"/>
		<input type="text" name="strArray"/>
		<input type="text" name="strArray"/>
		<input type="submit" value="提交"/>
	</form>

	<hr>

	listTest

	<form action="<c:url value="/test/listTest.action"/>" method="post">
		<%--<input type="checkbox" name="strList" value="1"/>--%>
		<%--<input type="checkbox" name="strList" value="2"/>--%>
		<%--<input type="checkbox" name="strList" value="3"/>--%>

		<input type="text" name="strList"/>
		<input type="text" name="strList"/>
		<input type="text" name="strList"/>
		<input type="submit" value="提交"/>
	</form>

	<form action="<c:url value="/test/listTest2.action"/>" method="post">
		<input type="text" name="strList"/>
		<input type="text" name="strList"/>
		<input type="text" name="strList"/>
		<input type="submit" value="提交"/>
	</form>

	<form action="<c:url value="/test/listTest3.action"/>" method="post">
		<input type="text" name="list[0]"/>
		<input type="text" name="list[1]"/>
		<input type="text" name="list[2]"/>
		<input type="submit" value="提交"/>
	</form>

	<form action="<c:url value="/test/listTest4.action"/>" method="post">
		<input type="text" name="inList[0].str"/>
		<input type="text" name="inList[1].str"/>
		<input type="text" name="inList[2].str"/>
		<input type="submit" value="提交"/>
	</form>

	<hr>

	mapTest

	<%--<form action="<c:url value="/test/mapTest.action"/>" method="post">
		<input type="text" name="strMap"/>
		<input type="text" name="strMap"/>
		<input type="text" name="strMap"/>
		<input type="submit" value="提交"/>
	</form>--%>
</body>
</html>
