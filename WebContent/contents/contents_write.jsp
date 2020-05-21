<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>
<form style="border-radius: 100px / 100px ;color:#F45D00;  background-color: rgba(355,355,355,0.8);width:450px;margin: auto;margin-top: 180px;" method="post" action="${cp }/contents/insert.do">
	<input type="hidden" value="${notice_num }" name="notice_num">
	<input type="hidden" value="${users_num }" name="users_num">
	<h2>제목 </h2>&nbsp;<input type="text" name="title"><br><br>
	<textarea rows="10" cols="50" name="post"></textarea><br>
	<input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="submit" value="작성하기">
	<input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="button" value="취소하기">
	<br>
</form><br>
</body>