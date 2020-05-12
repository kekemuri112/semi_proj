<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>comments.jsp</title>
</head>
<body>
<div>
	<c:forEach var="vo" items="${requestScope.comList }">
		${vo. }	
	</c:forEach>

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