<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1> 글보기 </h1>
<table border="1">
	<tr>
		<th>게시글번호</th><th>제목</th><th>작성자</th><th>작성일</th>
	</tr>
	<c:forEach var="vo" items="${list }">
	<tr>
		<td>${vo.contents_num}</td>
		<td><a href="${cp }/contents/detail.do?contents_num=${vo.contents_num}">${vo.contents_title }</a></td>
		<td>${vo.users_id }</td>
		<td>${vo.contents_modifyDate }</td>
	</tr>
	</c:forEach>
</table>
<form action="${cp }/contents/contents.do" method="post">
<select name="field">
<option value="users_id">글작성자</option>
<option value="contents_title">글제목</option>
<option value="contents_post">내용</option>
</select>
<input type="text" name="keyword">
<input type="submit" value="검색"><br>
<input type="hidden" value="${cafe_num }" name="cafe_num">
<input type="hidden" value="${notice_num }" name="notice_num">
</form>
<c:choose>
	<c:when test="${startPage>5}">
		<a href="${cp }/contents/contents.do?cafe_num=${cafe_num}&pageNum=${startPage-1}&notice_num=${notice_num}&field=${field}&keyword=${keyword}">[이전]</a>
	</c:when>
	<c:otherwise>
		[이전]
	</c:otherwise>
</c:choose>
<c:forEach	var="i" begin="${startPage}" end="${endPage}">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="${cp }/contents/contents.do?cafe_num=${cafe_num}&pageNum=${i}&notice_num=${notice_num}&field=${field}&keyword=${keyword}">[${i}]</a>
		</c:when>
		<c:otherwise>
			<a href="${cp }/contents/contents.do?cafe_num=${cafe_num}&pageNum=${i}&notice_num=${notice_num}&field=${field}&keyword=${keyword}">[${i}]</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test="${endPage<pageCount}">
		<a href="${cp }/contents/contents.do?cafe_num=${cafe_num}&pageNum=${endPage+1}&notice_num=${notice_num}&field=${field}&keyword=${keyword}">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
</c:choose>
<c:if test="${notice_num>0 }">
<a href="${cp }/contents/insert.do?cafe_num=${cafe_num}&notice_num=${notice_num}&field=${field}&keyword=${keyword}"><input type="button" value="글작성" ></a>
</c:if>
