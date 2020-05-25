<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	h2{display:inline-block;}
	.comments{border:2px solid black ;width:550px;display: inline-block;}
</style>
<br><br><br><br>
<div style="width:550px; margin:auto;  outline-style:solid ; outline-color:black;  background-color: rgba(255,255,255,0.8);">
	<h1 style="color: #F45D00;">상세글보기</h1>
	<h2>${vo.contents_title }</h2>&nbsp; &nbsp; <span>${vo.users_id }</span>&nbsp; &nbsp;
	<span>${vo.contents_regDate }</span><br>
	<div >
	<br>
	<p style="text-align: left;margin-left: 5px;">
	${vo.contents_post }
	</p>
	<br>
	</div>
	<br>
	<div style=" width:550px; margin:auto;">
	<c:if test="${vo.users_num eq sessionScope.users_num}">
		<a href="${cp }/contents/update.do?contents_num=${vo.contents_num}"><input type="button"  style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" value="수정하기"></a>
		<a href="${cp }/contents/delete.do?contents_num=${vo.contents_num}"><input type="button"  style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" value="삭제하기"></a>
	</c:if>
	<input style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;"  type="button" value="뒤로가기" onclick="return_contents();"><br>
	<br>
	</div>
</div>
<div>
	<jsp:include page="/comments/comments.jsp?contents_num=${vo.contents_num }&users_num=${vo.users_num }"/>
	<br><br>
</div>
<script>
	function return_contents(){
		history.go(-1);
	}
</script>

