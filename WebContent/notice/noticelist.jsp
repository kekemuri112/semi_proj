<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
	<h1>게시판목록</h1>
	<div>
		<c:forEach var="vo" items="${noticelist }">
			<c:choose>
				<c:when test="${vo.notice_lev==0 }">
					<a href="${cp }/contents/contents.do?notice_num=${vo.notice_num}">${vo.notice_name }</a>
					<a href="#">[+]</a><br>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="1" end="${vo.notice_lev }">
					 &ensp;&ensp;&ensp; 
					</c:forEach>
					└ <a href="${cp }/contents/contents.do?notice_num=${vo.notice_num}">${vo.notice_name }</a><br>
				</c:otherwise>
			</c:choose>
		
			
		</c:forEach>
		
	</div>
</body>