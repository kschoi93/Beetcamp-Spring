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
	function boardDel(){
		if(confirm("삭제하시겠습니까?")){
			location.href ="claseDel?no=${dto.no }";
		}
	}
</script>
</head>
<body>
<div class="container">
	<h1>답변형게시판 글 내용보기</h1>
	<ul>
		<li>번호 : ${dto.no }</li>
		<li>작성자 : ${dto.userid }</li>
		<li>등록일 : ${dto.writedate },조회수 : ${dto.hit }</li>
		<li>제목 : ${dto.subject }</li>
		<li>글내용<br/>${dto.content }</li>
	</ul>
	<div>
		<a href="claseEdit?no=${dto.no }">수정</a>
		<a href="javascript:boardDel()">삭제</a>
		<a href="claseWriteForm?no=${dto.no }">답글</a>
	</div>
	<div>
		<!-- 이전글 다음글 -->
		
		다음글 :
		<c:if test="${vo.prevNo==0}">
			${vo.prevSubject }
		</c:if>
		<c:if test="${vo.prevNo>0 }">
			<a href="claseView?no=${vo.prevNo }">${vo.prevSubject }</a>
		</c:if><br/>
		
		이전글 : 
		<c:if test="${vo.nextNo==0 }">
			${vo.nextSubject }
		</c:if>
		<c:if test="${vo.nextNo>0 }">
			<a href="claseView?no=${vo.nextNo }">${vo.nextSubject }</a>
		</c:if>
	</div>

<%-- 	<c:if test="${NoLagLead!=3 }"><!-- 답글이 아니면 나오게 해라 -->
		<div>
			이전글 : 
			<c:if test="<"${lagNo!= null}">
			
				<a href="claseView?no=${lagNo }">${lagName }</a>
			</c:if>
			<c:if test="${lagNo== null }">
				이전글이 없습니다.
			</c:if>
		</div>
		<div>
			다음글 : 
			<c:if test="${leadNo!= null}">
				<a href="claseView?no=${leadNo }">${leadName }</a>
			</c:if>
			<c:if test="${leadNo== null }">
				다음글이 없습니다.
			</c:if>
		</div>
	</c:if> --%>
</div>
</body>
</html>