<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeList.jsp</title>
</head>
<body>
	<h1>게시판목록</h1>
	<div>
		<c:forEach var="vo" items="${noticelist }">
			<c:if test="${vo.notice_lev>0 }">
				<c:forEach var="i" begin="1" end="${vo.notice_lev }">
					&nbsp;&nbsp;
				</c:forEach>
			</c:if>
			<a href="">${vo.notice_name }</a>
			
		</c:forEach>
	</div>
</body>
</html>