<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>comments.jsp</title>
<script type="text/javascript">
	var xhrList=null;
	function getList(){
		xhrList=new XMLHttpRequest();
		xhrList.onreadystatechange=listOk;
		xhrList.open('get', 'comments.do?${vo.contents_num}', true);
		xhrList.send();
	}
	function listOk(){
		if(xhrList.readyState==4 && xhrList.status==200){
			deleteAll();
			var xml=xhrList.responseXML;
			var comm=xml.getElementsByTagName("comm");
			var commList=document.getElementById("commList");
			for(var i=0;i<comments.length;i++){
				var id=comments[i].getElementsByTagName("id")[0].firstChild.nodeValue;
				var comments=comm[i].getElementsByTagName("comments")[0].firstChild.nodeValue;
				var div=document.createElement("div");
				var contents_num=comm[i].getElementsByTagName("contents_num")[0].firstChild.nodeValue;
				div.innerHTML=id+ ":"+ comments +
				"<a href='#' onclick='modifyCom("+contents_num+")'>수정</a>&nsb;";
				+ "<a href='#' onclick='deleteCom("+contents_num+")'>삭제</a>";				
			}
		}
	}
	var xhr=null;
	function insertComm(){
		xhr=new XMLHttpRequest();
		var user_id=request.getParameter("user_id");
		var comments_content=documnet.getElementById("comments_content");
		xhr.onreadystatechange=insertOk;
		xhr.open('get', 'comments/insert.do?user_id='+user_id+'&comments_content='+comments_content+'&contents_num=${vo.contents_num}',true);
	}
	function insertOk(){
		if(xhr.readyState==4 && xhr.status==200){
			var xml=xhr.responseXML;
			var code=xml.getElementsByName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				getList();
			}else{
				alert("오류로 인하여 작성에 실패하였습니다.");
			}
		}
	}
	function deleteAll(){
		var commentsList=document.getElementById("commentsList");
		var childs=commentsList.childNodes;
		var length=childs.length;
		for(var i=length-1;i>=0;i--){
			var comments=childs.item(i);
			commentsList.removeChild(comments_contents);
		}
	}
	var deleteComments=null;
	function deleteCom(comments_content, comments_num){
		deleteComments=new XMLHttpRequest();
		deleteComments.onreadystatechange=deleteOk;
		deleteComments.open('get','comments/delete.do?contents_num='+contents_num,true);
		deleteComments.send();
	}
	function deleteOk(){
		if(deleteComments.readyState==44 && deleteComments.status==200){
			var xml=deleteComments.responseXML;
			var code=xml.getElementById("code")[0].firstChild.nodeValue;
			if(code=='success'){
				getList();
			}else{
				alert("오류로 인하여 삭제에 실패하였습니다.");
			}
		}
	}
	var modifyComments=null;
	function modifyCom(comments_num){
		modifyComments=new XMLHttpRequest();
		modifyComments.onreadystatechange=modifyOk;
		modifyComments.open('get','/comments/modify.do?',true)
		modifyComments.send();
	}
	function modifyOk(){
		if(modifyComments.readyState==44 && modifyComments.status==200){
			var xml=modifyComments.responseXML;
			var code=xml.getElementById("code")[0].firstChild.nodeValue;
			if(code=='success'){
				getList();
			}else{
				alert("오류로 인하여 수정에 실패하였습니다.");
			}
		}
	}

</script>
</head>
<body>
<div>
	<div id="commentsList"></div>
	<div>
		${vo.user_id } &nsb; : 
		<textarea rows="3" cols="30" id="comments"></textarea><br>
		<input type="button" value="등록하기" onclick="insertComments()">
	</div>
</div>
<div><!-- 페이징 처리 -->
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
		<a href="${cp }/semi/comments.do?pageNum=${endPageNum+1">▶</a>	
	</c:if>
</div>
</body>
</html>