<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div style="border-radius: 100px / 100px ; background-color: rgba(355,355,355,0.8);width:450px;margin: auto;margin-top: 180px;">
	<br>
	<form method="post" action="${cp }/contents/update.do">
		<input type="hidden" value="${vo.users_num }" name="users_num">
		<input type="hidden" value="${vo.contents_num }" name="contents_num">
		
		<h1><label style="color: #F45D00; " for="subject">게시물 수정</label></h1><input type="text" name="contents_title" value="${vo.contents_title }"><br><br>
		
		<textarea  rows="10" cols="50" name="contents_post">${vo.contents_post }</textarea><br>
	
		<input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="submit" value="수정" >
		<input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="button"  value="취소" onclick="return_contents()">
		<br><br>
	</form>
</div>
<script>
	function return_contents(){
		history.go(-1);
	}
</script>
