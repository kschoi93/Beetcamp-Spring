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
	function boardDel(no){
		if(confirm("삭제하시겠습니까?")){
			location.href="boardDel?no=${vo.no}";
		}
	}
	
	$(function(){
		$(document).on('click',"#replyView input[value='수정']",function(){
			$("#replyView div:nth-child(1)").css('display','block');
			$("#replyView div:nth-child(2)").css('display','none');
			$(this).parent().css('display','none');
			$(this).parent().next().css('display','block');
			
		});
		
		$(document).on('submit','#replyView form',function(){
			var url = "boardReplyEdit";
			var params = $(this).serialize();
			
			$.ajax({
				url : url,
				data : params,
				type : "POST",
				success : function(){
					replyList();
				}, error : function(){
					console.log('댓글수정에러.....');
				}
			});
		})
		
		$(document).on('click',"#replyView input[value='삭제']",function(){
			if(confirm('댓글을 삭제하시겠습니까 ?')){
				var url = "boardReplyDelete";
				var params = "no="+$(this).parent().next().children().children().next().next().val();
				
				$.ajax({
					url:url,
					data : params,
					success : function(result){
						replyList();
					}, error : function(){
						console.log('삭제 에러 .. .. . . .');
					}
					
				})
			}
		});
		
		function replyList(){
			let url = "boardReplyList";
			let params = "no="+${vo.no};
			$.ajax({
				url:url,
				data:params,
				success: function(result){
					console.log(result);
					var $result = $(result);
					var tag = '<ul>';
					$result.each(function(idx,obj){
						tag+="<li style='border-bottom:1px solid #ddd'>";
						tag+="<div>" + obj.userid + " ("+ obj.writedate + ") ";
						if("${logId}"==obj.userid){
							tag += "<input type='button' value='수정'/>";
							tag += "<input type='button' value='삭제'/>";
						}
						tag += "<br/>"+obj.content+"</div>";
							tag += "<div style='display:none;'>";
								tag += "<form method='post' id='editFrm'>";
									tag += "<textarea name='content'>"+obj.content+"</textarea>";
									tag += "<input type='submit' value='수정하기'>"
									tag += "<input type='hidden' name='no' value='"+obj.no+"'/>";	
								tag += "</form>"
							tag += "</div>";
						tag += "</li>";
					});
					tag += "</ul>";
					
					$("#replyView").html(tag);
				},error:function(e){
					console.log(e);
				}
			})
		}//replyList
		
		$("#btn").click(function(){
			let url = "boardReplyInsert";
			let params = $("#writeFrm").serialize();
			if($("#content").val()==""){
				alert("글을 입력해주세요");
			}else{
				$.ajax({
					url:url,
					data:params,
					success:function(){
						$("#content").html("");
						replyList();
					}, error:function(e){
						console.log(e);
					}
				})
			}
		});//////////////btn
		replyList();
	});
</script>
</head>
<body>
<div class="container">
	<c:if test="${logId==null || logId=='' }">
		<a href="loginForm">로그인</a>
	</c:if>
	
	<h1>글내용보기</h1>
	번호 : ${vo.no }<br/>
	작성자 : : ${vo.userid }<br/>
	작성일 : ${vo.writedate }, 조회수 : ${vo.hit }<br/>
	제목 : ${vo.subject }<br/>
	${vo.content }<br/>
	<c:if test="${logId==vo.userid }">
		<a href="boardEdit?no=${vo.no }">수정</a>
		<a href="javascript:boardDel(${vo.no })">삭제</a>
	</c:if>
	<hr/>
	<div style="border-bottom:1px solid #ddd">
		<!-- 댓글 다는 공간 -->
		<form method="post" id="writeFrm">
			<input type="hidden" name="no" value="${vo.no }"/>
			<textarea name="content" id="content" style="width:100%"></textarea>
			<input type="button" id="btn" value="댓글작성"/>
		</form>
	</div>
	<div id="replyView"></div>
</div>
</body>
</html>