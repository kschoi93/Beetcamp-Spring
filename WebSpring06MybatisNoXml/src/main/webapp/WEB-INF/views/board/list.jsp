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
	ul,li{padding:0; margin:0; list-style-type:none;}
	li{width:10%;height:30px;float:left; line-height:30px; border-bottom:1px solid #ddd;}
	li:nth-child(5n+2){width:60%;}
	.wordcut{white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
</style>
<script>
	$(()=>{
		$('#listAllCheck').change(()=>{
			if($('#listAllCheck').prop('checked')){
				$('input[name="noList"]').prop('checked',true);
			} else {
				$('input[name="noList"]').prop('checked',false);
			}
		});
		//선택삭제가 클릭되면
		$("#delSelect").click(()=>{
			console.log('test');
			$('#delList').submit();
		})
	})
</script>
</head>
<body>
<div class="container">
	
	<h1>Oracle Mybatis noXml 리스트</h1>
	<c:if test="${logStatus=='Y' }">
		<a href="write">글쓰기</a>
	</c:if>
	<div>
		<form method="get" action="searchList">
			<select id="searchKey" name="searchKey">
				<option value="subject">제목</option>
				<option value="userid">작성자</option>
				<option value="content">글내용</option>
			</select>
			<input type="text" name="searchTxt" id="searchTxt"/>
			<input type="submit" value="Search...."/>
		</form>
	</div>
	
		<input type="checkbox" id="listAllCheck">전체선택
		<input type="button" value="선택삭제" id="delSelect"/>
		<form method="get" id="delList" action="multiDel">
		<ul>
			<li>번호</li>
			<li>제목</li>
			<li>작성자</li>
			<li>조회수</li>
			<li>작성일</li>
			
	
			<c:forEach var="list" items="${list }">			
				<li>
					<input type="checkbox" name="noList" value="${list.no}"/>
					${list.no }</li>
				<li class="wordcut"><a href="view?no=${list.no}">${list.subject }</a></li>
				<li>${list.userid }</li>
				<li>${list.hit }</li>
				<li>${list.writedate }</li>
			</c:forEach>
			
		</ul>
		</form>
	
</div>
</body>
</html>