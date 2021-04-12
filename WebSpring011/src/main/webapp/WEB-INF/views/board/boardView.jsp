<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	function delCheck(recordNo){
		if(confirm("삭제할까요?")){
			location.href="/home/boardDel?no="+recordNo;
		}
	}
	$(function(){
		$(document).on('click',"#comentView input[value='수정']",function(){
			$("#comentView div:nth-child(1)").css('display','block');
			$("#comentView div:nth-child(2)").css('display','none');
			$(this).parent().css('display','none');
			$(this).parent().next().css('display','block');
			
		});
		
		$(document).on('click',"#comentView input[value='삭제']",function(){
			if(confirm('댓글을 삭제하시겠습니까 ?')){
				var url = "/home/comentDelete";
				var params = "coment_no="+$(this).parent().next().children().children().next().next().val();
				
				$.ajax({
					url:url,
					data : params,
					success : function(result){
						comentList();
					}, error : function(){
						console.log('삭제 에러 .. .. . . .');
					}
					
				})
			}
		});
		
		$(document).on('submit','#comentView form',function(){
			var url = "/home/comentEdit";
			var params = $(this).serialize();
			
			$.ajax({
				url : url,
				data : params,
				type : "POST",
				success : function(){
					comentList();
				}, error : function(){
					console.log('댓글수정에러.....');
				}
			});
		})
		
		function comentList(){
			var url = "/home/comentAllSelect";
			var params = "board_no="+${vo.no};
			$.ajax({
				url:url,
				data:params,
				success : function(result){
					var $result = $(result);
					var tag = "<ul>";
					$result.each(function(idx,obj){
						tag += "<li style='border-bottom:1px solid #ddd'>";
						tag += "<div>" + obj.userid + " (" + obj.writedate + ") ";
						if("${logId}"==obj.userid){
							tag += "<input type='button' value='수정'/>";
							tag += "<input type='button' value='삭제'/>";
						}
						tag += "<br/>"+obj.coment_content+"</div>";
							tag += "<div style='display:none;'>";
								tag += "<form method='post' id='editFrm'>";
									tag += "<textarea name='coment_content'>"+obj.coment_content+"</textarea>";
									tag += "<input type='submit' value='수정하기'>"
									tag += "<input type='hidden' name='coment_no' value='"+obj.coment_no+"'/>";	
								tag += "</form>"
							tag += "</div>";
						tag += "</li>";
					});
					
					tag += "</ul>";
					
					$("#comentView").html(tag);
				},error:function(){
					console.log("댓글 불러오기 실패");
				}
			})
		}
		$("#comentBtn").click(function(){
			var url = "/home/comentInsert";
			var params = $("#comentFrm").serialize();
			if($("#coment_content").val()==""){
				alert("글을 입력해주세요");
			}else{
				$.ajax({
					url : url,
					data : params,
					success : function(result){
						comentList();
					}, error : function(e){
						console.log(e);
					}
					
				})
			}
		})
		comentList();
	})
	
</script>
<div class="container">
	<h1>글내용보기</h1>
	<ul>
		<li>번호 : ${vo.no }</li>
		<li>작성자 : ${vo.userid }</li>
		<li>등록일 : ${vo.writedate }, 조회수 : ${vo.hit }</li>
		<li>제목 : ${vo.subject }</li>
		<li>${vo.content }</li>
	</ul>
	<div style="border-bottom:1px solid #bbb">
		<c:if test="${logId==vo.userid }">
			<a href="/home/boardEdit?no=${vo.no }">수정</a>
			<a href="javascript:delCheck(${vo.no })">삭제</a>
		</c:if>
	</div>
	<!-- 댓글 -->
	<div>
		<c:if test="${logStatus=='Y' }">
			<form method="post" id="comentFrm">
				<input type="hidden" name="board_no" value="${vo.no }"/>
				<textarea id="coment_content" name="coment_content"></textarea>
				<input type="button" id="comentBtn" value="댓글작성"/>
			</form>
		</c:if>
	</div>
	<div id="comentView"></div>
</div>
</body>
</html>