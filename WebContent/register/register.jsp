<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/register/login.jsp</title>
</head>
<body>
	<div>
		<form method="post" action="/registercontroller.do">
			<table>
				<tr>
					<th colspan="2" style="font-size: 40px">회원 가입</th>
				</tr>
				<tr>
					<th>사용자 아이디</th>
					<td>
						<input type="text" name="users_id" id="users_id" onkeyup="idsearch()">
						<span id="checkId"></span>
					</td>
				</tr>			
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="users_pwd" id="users_pwd" onclick="pwdConfirm()">
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
					<td><input type="text" name="users_email"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td><input type="text" name="users_birth"></td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td><input type="text" name="users_phone"></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="가입하기">
						<input type="button" value="가입취소" onclick="regCancel()">
					</th>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	function idsearch() {
		var xhr=null;
		var users_id=document.getElementById("users_id").value;
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.status==200 && xhr.readyState==4){
				var xml=xhr.responseXML;
				var span=document.getElementById("checkId");
				var id=xml.getElementsByTagName("idcheck")[0].firstChild.nodeValue;
				if(id.length<5 && id.length<16){
					span.innerHTML=id;
				}else{
					span.innerHTML="길이가 옳바르지 않습니다.";
				}
			}
		};
		xhr.open("post", "idcheck.jsp?&users_id="+users_id, true);
		xhr.send();
	}
	
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