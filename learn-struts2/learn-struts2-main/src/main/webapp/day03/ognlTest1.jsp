<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>测试表单</title>
</head>
<body>
	<%--使用ognl+struts2标签计算字符串长度--%>
	<%--以下这些写法全部通过测试--%>
	<s:set var="str" value="'str'"/>
	<s:property value="%{#str.length}"/>
	<hr>

	<s:property value="'str'.length()"/>
	<br/>
	<s:property value="'str'.length"/>
	<br/>
	<s:property value="{1,2,3}.size()"/>
	<br/>
	<s:property value="{1,2,3}.size"/>
	<br/>
	<s:property value="%{'aaa'.length()}"/>
	<br/>
	<s:property value="%{'str'.length}"/>
</body>
</html>
