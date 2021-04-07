<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	function delCheck(recordNo){
		if(confirm("삭제할까요?")){
			location.href="/home/boardDel?no="+recordNo;
		}
		
	}
</script>
<div class="container">
	<h1>글내용보기</h1>
	<ul>
		<li>번호 : ${vo.no }</li><br/>
		<li>작성자 : ${vo.userid }</li><br/>
		<li>등록일 : ${vo.writedate }, 조회수 : ${vo.hit }</li><br/>
		<li>제목 : ${vo.subject }</li><br/>
		<li>${vo.content }</li>
	</ul>
	<div>
	<c:if test="${logId==vo.userid }">
		<a href="/home/boardEdit?no=${vo.no }">수정</a>
		<a href="javascript:delCheck(${vo.no })">삭제</a>
	</c:if>
	</div>
</div>
</body>
</html>