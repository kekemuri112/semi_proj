<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form method="post" action="${cp }/cafe/cafeinsert.do" enctype="multipart/form-data">
		카페이름<br>
		<input type="text" name="name"><br>
		카페소개글<br>
		<input type="text" name="desc"><br>
		개설의도<br>
		<input type="text" name="intent"><br>
		이미지<br>
		<input type="file" name="image"><br>
		<input type="submit" value="개설요청">
	</form>
</div>
