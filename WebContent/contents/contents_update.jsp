<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form method="post" action="${cp }/contents/update.do">
	<input type="hidden" value="${vo.users_num }" name="users_num">
	<input type="hidden" value="${vo.contents_num }" name="contents_num">
	<h2>제목 </h2>&nbsp;|<input type="text" name="contents_title" value="${vo.contents_title }"><br><br>
	<textarea rows="10" cols="50" name="contents_post">${vo.contents_post }</textarea><br>
	<input type="submit" value="작성하기"><input type="button" value="취소하기" onclick="return_contents()">
</form>
<script>
	function return_contents(){
		history.go(-1);
	}
</script>