<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contents_num=request.getParameter("contents_num");
	int users_num=Integer.parseInt(request.getParameter("users_num"));
%>
<div id="commList">
</div>
	<div>
		<textarea rows="3" cols="30" id="comments"></textarea><br>
		<input type="button" value="등록하기" onclick="comments_write()">
	</div>
<div><!-- 페이징 처리
	<c:if test="${startPageNum>5 }">
		<a href="${cp }/semi/comments.do?pageNum=${startPageNum-1}">◀</a>
	</c:if>
	<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="${cp }/semi/comments.do?pageNum=${i}">
				<span style='color:blue'>[${i}]</span></a>
			</c:when>
			<c:otherwise>
				<a href="${cp }/semi/comments.do?pageNum=${i}">
				<span style='color:gray'>[${i}]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${pageCount>endPageNum }">
		<a href="${cp }/semi/comments.do?pageNum=${endPageNum+1}">▶</a>	
	</c:if> -->
</div>
<script type="text/javascript">
	window.onload=getList;
	function getList(){
		console.log("list 함수 실행!!");
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				var commList=document.getElementById("commList");
				for(var i=0;i<json.length;i++){
					var users_id=json[i].users_id;
					var comments_content=json[i].comments_content;
					var comments_num=json[i].comments_num;
					var div=document.createElement("div");
					div.innerHTML="<strong>작성자 : </strong>"+users_id+"<br>"
						         +"내용 : "+comments_content+"<br>"
						        +"<input type='button' value='수정하기' ><input type='button' value='삭제하기'>";
					div.className="comments"
					commList.appendChild(div);
				}
			}
		}
		xhr.open('get', '${cp}/comments/comments.do?contents_num=<%=contents_num%>&users_num=<%=users_num%>', true);
		xhr.send();
	}
	function comments_write(){
		var textarea_comments_content=document.getElementById("comments")
		var comments_content=textarea_comments_content.value;
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			
		}
		xhr.open();
		xhr.send();
	}
	
</script>