<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	ul{ list-style:none;};
</style>
<h1 style="color: white;text-align: center;">카페목록</h1>
<div> 
	<ul>
		<c:forEach var="vo" items="${cafelist }">
			<a href="${cp }/contents/contents.do?cafe_num=${vo.cafe_num }&cafe_name=${vo.cafe_name}">
			<li>${vo.cafe_name }</li></a>
		</c:forEach>
		<c:choose>
			<c:when test="${users_id eq 'admin' }">
				<a href="${cp }/semi/pagecontroller.do?check=7"><li style="color: white; font-size:30px;  text-decoration:none; ">카페승인대기리스트</li></a>
			</c:when>
			<c:when test="${not empty users_id }">
				<a href="${cp }/cafe/cafeinsert.do"><li style="color: white; font-size:30px;  text-decoration:none; ">카페개설요청</li></a>
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>
	</ul>
</div>
