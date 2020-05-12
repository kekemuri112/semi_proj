<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/register/regupdate.jsp</title>
</head>
<body>
	<div>
		<form method="post" action="/reg/updatecontroller.do">
			<table>
				<tr>
					<th colspan="2" style="font-size: 40px">${vo.getUsers_id }회원 정보 수정</th>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="users_pwd" id="users_pwd" value="${vo.getUsers_pwd }" onclick="pwdConfirm()">
						<span id="pwdCon"></span>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" id="pwd2" onkeyup="pwdCheck()">
						<span id="checkPwd"></span>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="users_email" value="${vo.getUsers_email }"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="text" name="users_birth" value="${vo.getUsers_birth }"></td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td><input type="text" name="users_phone" value="${vo.getUsers_phone }"></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="수정하기">
						<input type="button" value="수정취소" onclick="regCancel()">
					</th>
				</tr>
			</table>
		</form>
	</div>	
</body>
<script type="text/javascript">
	function pwdCheck(){
		var pwd1=document.getElementById("users_pwd").value;
		var pwd2=document.getElementById("pwd2").value;
		var span=document.getElementById("checkPwd");
		if(pwd1==pwd2){
			span.innerHTML="일치";
			span.style.color="blue";
		}else{
			span.innerHTML="불일치";
			span.style.color="red";
		}
	}
	
	function pwdConfirm(){
		var pwd=document.getElementById("users_pwd").value;
		var span=document.getElementById("pwdCon");
		if(pwd.length<5||pwd.length>13){
			span.innerHTML="6~12자리사이";
			span.style.color="red";
		}else{
			span.innerHTML="사용가능";
			span.style.color="blue";
		}
	}
	
	function regCancel(){
		window.history.back();
	}

</script>
</html>