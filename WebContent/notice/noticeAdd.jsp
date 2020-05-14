<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeAdd.jsp</title>
</head>
<body>
<h1>노티스넘 파라미터 넘어옴,Notice만들기</h1>
	<div>
		<form method="post" action="$/notice/noticeinsert.do">
		<input type="hidden" name="notice_num" value="${vo.notice_num}">
		<input type="hidden" name="cafe_num" value="${vo.cafe_num}">
		<input type="hidden" name="notice_name" value="${vo.notice_name}">
		<input type="hidden" name="notice_lev" value="${vo.notice_lev}">
		<input type="hidden" name="notice_ref" value="${vo.notice_ref}">
		<input type="hidden" name="notice_step" value="${vo.notice_step}">
		<input type="hidden" name="notice_grade" value="${vo.notice_grade}">
			게시판이름<br>
			<input type="text" name="name"><br>
			게시판 등급<br>
			<input type="text" name="grade"><br>
			<input type="submit" value="개설">
		</form>
	</div>
</body>
</html>