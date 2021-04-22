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
<script>
	$(()=>{
		$("#boardDel").click(()=>{
			if(confirm("삭제하시겠습니까?")){
				location.href="boardDel?no=${vo.no}";
			}else{
				return false;
			}
		});
	})
</script>
</head>
<body>
<div class="container">
	<h1>글 보기</h1>
	<ul>
		<li>번호 : ${vo.no }</li>
		<li>작성자 : ${vo.userid }</li>
		<li>작성일 : ${vo.writedate }</li>
		<li>제목 : ${vo.subject }</li>
		<li>글 내용: <br/>
			${vo.content }</li>
	</ul>
	<div>
		<c:if test="${logId==vo.userid }">
			<a href="boardEdit?no=${vo.no }">수정</a>
			<a href="#" id="boardDel">삭제</a>
		</c:if>
	</div>
</div>
</body>
</html>