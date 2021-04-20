<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=dvice-width, initial-scale=1"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
<style>
	ul,li{
		margin: 0;
		padding: 0;
		list-style-type: none;
	}
	#boardList li{
		width: 10%;
		height: 40px;
		line-height: 40px;
		border-bottom: 1px solid #ddd;
		float:left;
	}
	
	#boardList li:nth-child(5n+2){
		width:60%;
	}	
	
	.wordCut{
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
</style>
</head>
<body>
<div class="container">
	<h1>게시판 리스트</h1>
	<c:if test="${logVo!=null && logVo!=''}">
		<a href="boardWrite">글쓰기</a>
	</c:if>
	<ul id="boardList">
		<li>번호</li>
		<li>제목</li>
		<li>글쓴이</li>
		<li>조회수</li>
		<li>등록일</li>
		
		<c:forEach var="lst" items="${list}">
			<li>${lst.no }</li>
			<li class="wordCut"><a href="boardView?no=${lst.no }">${lst.subject }</a></li>
			<li>${lst.userid }</li>
			<li>${lst.hit }</li>
			<li>${lst.writedate }</li>
		</c:forEach>
	</ul>
</div>
</body>
</html>