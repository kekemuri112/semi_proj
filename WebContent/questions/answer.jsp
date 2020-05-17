<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>questions/answer.jsp</title>
</head>
<body>
	<div>
		<h1>카페 가입 질문</h1>
	</div>
	<form method="post" action="/questions/answer.do">
		<input type="hidden" value="${cafereg_num }">
		<c:forEach var="sub" items="${list }">
			<textarea rows="3" cols="30">${sub }</textarea><br>
			<input type="text" name="answer"><br>
		</c:forEach>
		<input type="submit" value="등록">
	</form>
</body>
</html>