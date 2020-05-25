<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="border-radius: 70px / 70px ; background-color: rgba(355,355,355,0.8);width:600px;margin: auto;margin-top: 180px;">
	<br>
	<h1>${cafe_name } 를 탈퇴하시겠습니까?</h1><br><br>
	<form method="get" action="${cp }/cafe/usersdel.do">
		<input  style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;"  type="submit" value="탈퇴" ><br><br>
	</form>
	<input  style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="button" value="취소" onclick="cancel()"><br>
	<br><br>
</div>
<script type="text/javascript">
	function cancel(){
		window.history.back();
	}
</script>