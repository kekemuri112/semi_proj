<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>${cafe_name } 를 탈퇴하시겠습니까?</h1><br><br>
<form method="get" action="${cp }/cafe/usersdel.do">
	<input type="submit" value="탈퇴" ><br><br>
</form>
<input type="button" value="취소" onclick="cancel()"><br>

<script type="text/javascript">
	function cancel(){
		window.history.back();
	}
</script>