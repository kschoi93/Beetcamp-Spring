<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js_css/style.css" type="text/css"/>
	<script src="<%=request.getContextPath()%>/js_css/script.js"></script>
</head>
<body>
	<a href="/myapp/aLink?name=홍길동&age=25">클릭하세요..1</a>
	<a href="/myapp/aLink2?name=이순신&age=43">클릭하세요..2</a>
	<a href="/myapp/aLink3?username=사명대사&age=78">클릭하세요..3</a>
	<a href="/myapp/aLink4?username=세종대왕&age=53">클릭하세요..4</a>
	<a href="/myapp/formData">클릭하세요.. 폼으로 이동</a>

<img src="<%=request.getContextPath()%>/img/4.jpg"/>
<!-- 
	web.xml
	1. dispatcher 시작
	2. mapping
	3. controller
	3. return : app servlet -> servlet-context.xml
	4. 다시 dispatcher servlet에 보내고 view로 출력 

 -->
</body>
</html>
