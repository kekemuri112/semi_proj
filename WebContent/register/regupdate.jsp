<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form method="post" action="${cp }/reg/updatecontroller.do">
		<table>
			<tr>
				<th colspan="2" style="font-size: 40px">${vo.users_id } 회원 정보 수정<br></th>
			</tr>
			<tr>
				<th>이름</th>
				<td>
				<input type="text" name="users_name" id="users_name" value="${vo.users_name }" disabled="disabled"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="users_pwd" id="users_pwd" onkeyup="pwdConfirm()" onfocusout="delmsg()">
					<span id="pwdCon"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" id="pwd2" onkeyup="pwdCheck()" onfocusout="delmsg()">
					<span id="checkPwd"></span>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="users_email" placeholder="${vo.users_email}" ></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="text" name="users_birth" value="${vo.users_birth}" disabled="disabled"></td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td><input type="text" name="users_phone" placeholder="${vo.users_phone}"></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="hidden" name="users_num" id="users_num" value="${vo.users_num }">
					<br>
					<input type="submit" value="수정하기">
					<input type="button" value="수정취소" onclick="regCancel()"><br>
					
				</th>
			</tr>
		</table>
	</form>
</div>	

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
		var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
		var blank_pattern = /[\s]/g;
		if(pwd.length<6||pwd.length>13){
			span.innerHTML="6~12자리 이루어진 비밀번호를 생성하세요.";
			span.style.color="red";
		}else{
			span.innerHTML="사용가능";
			span.style.color="blue";
		}
		if(special_pattern.test(pwd)|| blank_pattern.test(pwd)){
			span.innerHTML="특수문자나 공백을 사용할 수 없습니다.";
			span.style.color="red";
		}
	}
	
	function regCancel(){
		window.history.back();
	}
	function delmsg(){
		var span1=document.getElementById("checkPwd");
		var span2=document.getElementById("pwdCon");
		span1.innerHTML="";
		span2.innerHTML="";
	}
</script>
