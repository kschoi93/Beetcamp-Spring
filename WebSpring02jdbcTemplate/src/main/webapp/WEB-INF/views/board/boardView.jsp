<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	function del(){
		if(confirm("삭제하시겠습니까?")){
			location.href = "/jdbc/boardDel?no=${vo.no}";	
		}
	}
	
	//ajax를 이용한 댓글처리
	$(function(){
		//댓글목록선택
		function replyList(){
			//서버에서 해당글의 댓글을 선택하여
			var url = "/jdbc/replyList";
			var params = "no=${vo.no}";
			$.ajax({
				url : url,
				data : params,
				success : function(result){
					var $result = $(result);
					
					var tag="<ul>";
					$result.each(function(idx,obj){
						tag+= "<li style='border-bottom:1px solid #ddd'>";
						tag+= "<div>"+obj.userid + "(" + obj.replydate + ") ";
						if(obj.userid=="${logId}"){
							tag+= "<input type='button' value='수정'/>";
							tag+= "<input type='button' value='삭제' title="+obj.num+"/>";
						}
						tag+= "<br/>"+ obj.content+"</div>";
						
						if(obj.userid=="${logId}"){
							// 수정폼
							tag += "<div style='display:none;'>";
								tag += "<form method='post'>";
									tag += "<textarea name='content' style='width:500px;height:80px;'>"+obj.content+"</textarea>";
									tag += "<input type='submit' value='수정하기'/>"
									tag += "<input type='hidden' name='num' value='"+obj.num+"'/>";
								tag += "</form></div>";
						}
						
						tag+="</li>";
					});
					tag += "</ul>";
					
					$('#replyList').html(tag);
				},error : function(){
					console.log('데이터 가져오기 에러');
				}
			})
		}
		//댓글쓰기
		$("#replyWriteBtn").click(function(){
			if($("#content").val()!=''){
				var url = "/jdbc/replyWriteOk";
				var params =$("#replyWriteFrm").serialize(); // 데이터 .. no181&content=작성중
					
				$.ajax({
					url : url,
					data : params,
					success : function(result){
						replyList();
						$("#content").val("");
						
						console.log('댓글등록성공'+result);
					}, error : function(e){
						console.log(e.responseTest);
					}
				});
			} else{
				alert("댓글을 입력후 저장하세요..");
			}
		});
		//글수정
		$(document).on('submit','#replyList form',function(){
			var url = "/jdbc/replyEditOk";
			var params = $(this).serialize();//content=090&num=254
			
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
		
		//댓글 삭제
		$(document).on('click','#replyList input[value="삭제"]',function(){
			if(confirm('댓글을 삭제하시겠습니까 ?')){
				var url = "/jdbc/replyDeleteOk";
				var params = "num="+$(this).parent().next().children().children().next().next().val();
				//var params = "num="+$(this).attr("title");
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
		})
		
		replyList();//글내용보기가 실행되면 댓글이 #ajax로 실행됨
		
		//수정버튼 선택하면 수정폼 보여주기
		$(document).on('click','#replyList input[value="수정"]',function(){
			$('#replyList>ul>li>div:nth-child(1)').css("display","block");
			$('#replyList>ul>li>div:nth-child(2)').css('display','none');
			$(this).parent().css("display","none");
			$(this).parent().next().css("display","block");
		});
		
	});
	
	
</script>
<div class="container">
	<h1>글내용보기</h1>
	<ul>
		<li>번호 : ${vo.no }</li>
		<li>작성자 : ${vo.userid }</li>
		<li>작성일 : ${vo.writedate }</li>
		<li>조회수 : ${vo.hit }</li>
		<li>제목 : ${vo.subject }</li>
		<li>글내용 : ${vo.content }</li>
	</ul>
	<div>
		<c:if test="${logStatus=='Y' && logId==vo.userid}">
			<a href="/jdbc/boardEdit?no=${vo.no }">수정</a>
			<a href="javascript:del()">삭제</a>
		</c:if>
	</div>
	<hr/>
	<!-- 댓글폼 -->
	<div>
		<c:if test="${logStatus=='Y' }"><!-- 로그인 된 경우 댓글쓰기 가능 -->
			<div>
				<form method="post" id="replyWriteFrm">
					<input type="hidden" name="no" value="${vo.no }"/>
					<textarea id="content" name="content" style="width:500px;height:100px;margin:0;padding:0;"></textarea>
					<input type="button" value="댓글등록" id="replyWriteBtn"/>
				</form>
			</div>
		</c:if>
	</div>
	<!-- 댓글리스트 -->
	<div id="replyList"></div>
</div>
</body>
</html>