<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${empty notice_name}">
		<h1 style="font-size: 80px; color:#F45D00; " > 전체글보기 </h1>		
	</c:when>
	<c:otherwise>
		<h1> ${notice_name } </h1>
	</c:otherwise>
</c:choose>

<table style="font-size:27px; background-color: rgba(255,255,255,0.8);" border="1">
	<tr>
		<th>게시글번호</th><th style="width: 200px">제목</th><th>작성자</th><th>작성일</th>
	</tr>
	<c:forEach var="vo" items="${list }">
	<tr>
		<td>${vo.contents_num}</td>
		<td><a href="${cp }/contents/detail.do?contents_num=${vo.contents_num}">${vo.contents_title }[${vo.count }]</a></td>
		<td>${vo.users_id }</td>
		<td>${vo.contents_modifyDate }</td>
	</tr>
	</c:forEach>
</table><br>
<form action="${cp }/contents/contents.do" method="post">
	<select name="field">
		<option value="users_id" >작성자</option>
		<option value="contents_title">제목</option>
		<option value="contents_post">내용</option>
	</select>
	<input type="text" name="keyword">
	<input style=" width:65px;height:27px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="submit" value="검색"><br>
	<input type="hidden" value="${notice_num }" name="notice_num">
</form>
<c:choose>
	<c:when test="${startPage>5}">
		<a href="${cp }/contents/contents.do?pageNum=${startPage-1}&notice_num=${notice_num}&keyword=${keyword}&field=${field}">[이전]</a>
	</c:when>
	<c:otherwise>
		[이전]
	</c:otherwise>
</c:choose>
<c:forEach	var="i" begin="${startPage}" end="${endPage}">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="${cp }/contents/contents.do?pageNum=${i}&notice_num=${notice_num}&keyword=${keyword}&field=${field}">[${i}]</a>
		</c:when>
		<c:otherwise>
			<a href="${cp }/contents/contents.do?pageNum=${i}&notice_num=${notice_num}&keyword=${keyword}&field=${field}">[${i}]</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test="${endPage<pageCount}">
		<a href="${cp }/contents/contents.do?pageNum=${endPage+1}&notice_num=${notice_num}&keyword=${keyword}&field=${field}">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
</c:choose>
<c:if test="${(notice_num>0 && userscafe eq 'true') || (cafe_admin eq 'true'&& notice_num>0)}">
	<a href="${cp }/contents/insert.do?notice_num=${notice_num}"><input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="button" value="글작성" ></a>
</c:if>
