<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form style="border-radius: 100px / 100px ; background-color: rgba(355,355,355,0.8);width:450px;margin: auto;margin-top: 180px;" method="post" action="${cp }/reg/logincontroller.do">
		<table>
			<tr>
				<th colspan="2" style="color:#F45D00 ; font-size: 100px">로 그 인</th>
			</tr>
			<tr></tr>
			<tr>
				<th style="color:#F45D00 ;">아이디</th>
				<td><input type="text" name="users_id"></td>
			</tr>			
			<tr>
				<th style="color:#F45D00 ;" >비밀번호</th>
				<td><input type="password" name="users_pwd"></td>
			</tr>
			<tr>
				<th colspan="2">
					<input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;"  type="submit" type="submit" value="로그인">
					<input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;"  type="button" type="button" value="뒤로가기" onclick="loginCancel()">
				</th>
			</tr>
			<tr></tr>
		</table>
		<br>
	</form>
</div>
<script type="text/javascript">
	function loginCancel(){
		window.history.back();
	}
</script>
