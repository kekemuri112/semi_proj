<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
h2{display:inline-block;}
.comments{border:2px solid black ;width:450px;}
</style>
<h1>상세글보기</h1>
<h2>${vo.contents_title }</h2>&nbsp; &nbsp; <span>${vo.users_id }</span>&nbsp; &nbsp;
<span>${vo.contents_regDate }</span><br>
===================================================================
<p>
${vo.contents_post }
</p>
===================================================================
<br>
<c:if test="${vo.users_num eq sessionScope.users_num}">
	<a href="${cp }/contents/update.do?contents_num=${vo.contents_num}"><input type="button" value="수정하기"></a>
	<a href="#"><input type="button" value="삭제하기"></a>
</c:if>
<input type="button" value="뒤로가기" onclick="return_contents()"><br>
<div>
	<jsp:include page="../comments/comments.jsp?contents_num=${vo.contents_num }&users_num=${vo.users_num }"/>
</div>
<script>
	function return_contents(){
		history.go(-1);
	}
</script>

