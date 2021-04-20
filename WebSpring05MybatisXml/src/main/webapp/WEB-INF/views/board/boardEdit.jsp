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
<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
<script>
	$(function(){
		$("#boardFrm").on('submit',function(){
			//(document).getElementById("subject").value
			//document.boardFrm.subject.value
			///$("#subject").val()
			if($("#subject").val()==""){
				return false;
			}
			
			if(CKEDITOR.instances.content.getData()==""){
				return false;
			}
			return true;
		})
	})
</script>
</head>
<body>
<div class="container">
	<h1>글 내용보기</h1>
	<form method="post" action="boardEditOk">
	<input type="hidden" name="no" value="${vo.no }"/>
		<ul>
			<li>글제목 : <input type="text" name="subject" value="${vo.subject }"/></li>
			<li>글내용 : <br/>
			<textarea name="content">${vo.content }</textarea></li>
		</ul>
		<script>
					CKEDITOR.replace("content");
				</script>
			<input type="submit" value="글수정"/>
	</form>
</div>
</body>
</html>