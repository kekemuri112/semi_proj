<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeInsert.jsp</title>
</head>
<body>
	<div>
		<form method="post" action="$/cafe/cafeinsert.do" enctype="multipart/form-data">
			게시판이름<br>
			<input type="text" name="name"><br>
			게시판 등급<br>
			<input type="text" name="grade"><br>
			<input type="submit" value="개설">
		</form>
	</div>
</body>
</html>