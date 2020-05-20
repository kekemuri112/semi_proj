<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	h2{display:inline-block;}
	.comments{border:2px solid black ;width:550px;display: inline-block;}
</style>
<div style="width:550px; margin:auto;  outline-style:double; outline-color:black;  background-color: rgba(255,255,255,0.3);">
	<h1>상세글보기</h1>
	<h2>${vo.contents_title }</h2>&nbsp; &nbsp; <span>${vo.users_id }</span>&nbsp; &nbsp;
	<span>${vo.contents_regDate }</span><br>
	<div style="background-color: rgba(255,255,255,0.3);  outline-style: double;outline-color: black;">
	<p>
	${vo.post }
	</p>
	</div>
	<br>
	<c:if test="${vo.users_num eq sessionScope.users_num}">
		<form width:550px; margin:auto;  outline-style:double; outline-color:black;  background-color: rgba(255,255,255,0.3); action="/contents/update.do?contents_num=${vo.contents_num }&users_num=${vo.users_num}" method="get">
			<input type="hidden" name="contents_num" value="${vo.contents_num }">
			<input type="submit" value="수정하기">
		</form>
		<input type="button" value="삭제하기" onclick="">
	</c:if>
	<br>
	<input type="button" value="뒤로가기" onclick="return_contents();"><br>
	<br>
</div>
<div style="background-color: rgba(255,255,255,0.3);">
	<jsp:include page="../comments/comments.jsp?contents_num=${vo.contents_num }&users_num=${vo.users_num }"/>
	<br><br>
</div>
<script>
	function return_contents(){
		history.go(-1);
	}
</script>

