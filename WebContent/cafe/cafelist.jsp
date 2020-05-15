<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>카페목록</h1>
<div>
	<ul>
		<c:forEach var="vo" items="${cafelist}">
			<a href="${cp }/notice/noticelist.do?cafe_num=${vo.cafe_num }&cafe_name=${vo.cafe_name}">
			<li>${vo.cafe_name }</li></a>
		</c:forEach>
		<c:choose>
			<c:when test="${not empty users_id }">
				<a href="${cp }/cafe/cafeinsert.do"><li>카페개설요청</li></a>
			</c:when>
			<c:when test="${users_id=='admin' }">
				<a href="${cp }/semi/pagecontroller.do?check=7"><li>카페승인대기리스트</li></a>
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>
	</ul>
</div>
