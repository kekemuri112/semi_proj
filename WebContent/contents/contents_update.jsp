<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<form method="post" action="${cp }/contents/update.do">
	<input type="hidden" value="${vo.users_num }" name="users_num">
	<input type="hidden" value="${vo.contents_num }" name="contents_num">
	
	<h1><label for="subject">제목</label></h1><input type="text" name="contents_title" value="${vo.contents_title }"><br><br>
	
	<textarea  rows="10" cols="50" name="contents_post">${vo.contents_post }</textarea><br>

	<input type="submit" value="수정" ><input type="button"  value="취소" onclick="return_contents()">
</form>
<script>
	function return_contents(){
		history.go(-1);
	}
</script>
