<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/contents/detail.jsp</title>
<style>
h2{display:inline-block;}
</style>
</head>
<body>
<h1>상세글보기</h1>

<h2>${vo.contents_title }</h2>  <span>${vo.contents_regDate }</span><br>
===================================================================
<p>
${vo.post }
</p>

===================================================================
<br>
<input type="button" value="수정"><input type="button" value="삭제">
</body>
</html>