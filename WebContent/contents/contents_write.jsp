<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.form-group{width:600px}

</style>
<div class="container">
<form method="post" action="${cp }/contents/insert.do">
<input type="hidden" value="${notice_num }" name="notice_num">
<input type="hidden" value="${users_num }" name="users_num">
<div class="form-group">
<label for="subject">제목</label>
<input type="text" name="contents_title" class="form-control" placeholder="제목을 입력하세요">
</div>
<div class="form-group">
<textarea class="form-control" rows="10" cols="50" name="contents_post"></textarea>
</div>
<input type="submit" value="작성하기" class="btn btn-primary"><input type="button" class="btn" value="취소하기" onclick="return_contents()">
</form>
</div>
<script>
	function return_contents(){
		history.go(-1);
	}
</script>
<%@ include file="bootstrap.jsp"%>