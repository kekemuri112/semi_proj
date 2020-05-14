<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<h1>카페목록</h1>
	<div>
	<ul>
	<c:forEach var="vo" items="${cafelist}">
		<a href="${cp }/notice/noticelist.do?cafe_num=${vo.cafe_num }&cafe_name=${vo.cafe_name}"><li>${vo.cafe_name }</li></a>
	</c:forEach>
	</ul>
	</div>
</body>