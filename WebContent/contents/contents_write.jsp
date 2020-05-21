<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form method="post" action="${cp }/contents/insert.do">
	<input type="hidden" value="${notice_num }" name="notice_num">
	<input type="hidden" value="${users_num }" name="users_num">
	<h2>제목 </h2>&nbsp;<input type="text" name="title"><br><br>
	<textarea rows="10" cols="50" name="post"></textarea><br>
	<input type="submit" value="작성하기"><input type="button" value="취소하기">
</form>