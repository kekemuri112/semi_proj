<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
h2{display:inline-block;}
</style>
<h1>상세글보기</h1>
<h2>${vo.contents_title }</h2>&nbsp; &nbsp; <span>${vo.users_id }</span>&nbsp; &nbsp;
<span>${vo.contents_regDate }</span><br>
===================================================================
<p>
${vo.post }
</p>
===================================================================
<br>
<c:if test="${vo.users_num eq sessionScope.users_num}">
	<form action="/contents/update.do?contents_num=${vo.contents_num }&users_num=${vo.users_num}" method="get">
		<input type="hidden" name="contents_num" value="${vo.contents_num }">
		<input type="submit" value="수정하기">
	</form>
	<input type="button" value="삭제하기" onclick="">
</c:if>
<a href="#"><input type="button" value="뒤로가기"></a>