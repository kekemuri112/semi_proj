<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.form-group{width:600px}

</style>
<div class="container">
<form method="post" action="${cp }/contents/update.do">
	<input type="hidden" value="${vo.users_num }" name="users_num">
	<input type="hidden" value="${vo.contents_num }" name="contents_num">
	<div class="form-group">
	<h1><label for="subject">제목</label></h1><input type="text" name="contents_title" class="form-control" value="${vo.contents_title }"><br><br>
	<div class="form-group">
	<textarea class="form-control" rows="10" cols="50" name="contents_post">${vo.contents_post }</textarea><br>
	</div>
	<input type="submit" value="수정" class="btn btn-primary"><input type="button" class="btn" value="취소" onclick="return_contents()">
</form>
</div>
<script>
	function return_contents(){
		history.go(-1);
	}
</script>
<%@ include file="bootstrap.jsp"%>