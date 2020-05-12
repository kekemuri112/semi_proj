<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<c:if test="${sessionScope.id==vo.users_id }">
<a href="#"><input type="button" value="수정"></a><a href="#"><input type="button" value="삭제"></a>
</c:if>