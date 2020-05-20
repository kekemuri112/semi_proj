<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form method="post" action="${cp }/reg/registercontroller.do" onsubmit="return check()">
		<table>
			<tr>
				<th colspan="2" style="font-size: 40px">회원 가입</th>
			</tr>
			<tr>
				<th>사용자 아이디</th>
				<td>
					<input type="text" name="users_id" id="users_id" placeholder="아이디 입력(6~14자리)" minlength="6" onkeyup="idsearch()"
					onfocusout="delmsg()">
					<span id="checkId"></span>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="users_name" id="users_name"></td>
			</tr>				
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="users_pwd" id="users_pwd" placeholder="비밀번호 입력(6~12자리)" minlength="6" maxlength="12" onkeyup="pwdConfirm()"
					onfocusout="delmsg()">
					<span id="pwdCon"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" id="pwd2" onkeyup="pwdCheck()" maxlength="12" onfocusout="delmsg()">
					<span id="checkPwd"></span>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="users_email"></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="text" name="users_birth" placeholder="19000101로 작성" maxlength="8"></td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td><input type="text" name="users_phone" placeholder="-빼고 입력"></td>
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
<script type="text/javascript">
	var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	var blank_pattern = /[\s]/g;
	var users_id=document.getElementById("users_id").value;
	var bl=eval('false');
	var bl2=eval('false');
	var bl3=eval('false');
	
	function check(){
		if(bl&&bl2&&bl3){
			return true;
		}else{
			return false;	
		}
	}
	function idsearch() {
		var xhr=null;
		var users_id=document.getElementById("users_id").value;
		var span=document.getElementById("checkId");
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.status==200 && xhr.readyState==4){
				var xml=xhr.responseXML;
				var check=xml.getElementsByTagName("idcheck")[0].firstChild.nodeValue;
				if(check=='true' && users_id.length>=6 && users_id.length<=14){
					span.innerHTML = "사용 가능한 아이디입니다.";
					span.style.color="blue";
					bl=true;
				}else if(special_pattern.test(users_id)|| blank_pattern.test(users_id)){
					span.innerHTML="아이디엔 특수문자나 공백을 사용할 수 없습니다.";
					span.style.color="red";
					bl=false;
				}else if(typeof users_id=="undefined"||users_id==null||users_id==""){
					span.innerHTML="필수 정보입니다.";
					span.style.color="red";
					bl=false;
				}else if(check=='false'){
					span.innerHTML = "중복된 아이디입니다.";
					span.style.color="red";
					bl=false;
				}else{
					span.innerHTML="길이가 올바르지 않습니다.";
					span.style.color="red";
					bl=false;
				}
			}
		};
		xhr.open("post", "${cp }/reg/idcheck.do?&users_id="+users_id, true);
		xhr.send();
	}
	
	/*
	function idResult(){
		var span=document.getElementById("checkId");
		if(special_pattern.test(users_id)|| blank_pattern.test(users_id)){
			span.innerHTML="아이디엔 특수문자나 공백을 사용할 수 없습니다.";
			span.style.color="red";
			bl2=false;
		}else if(typeof users_id=="undefined"||users_id==null||users_id==""){
			span.innerHTML="필수 정보입니다.";
			span.style.color="red";
			bl2=false;
		}else{
			span.innerHTML="길이가 올바르지 않습니다.";
			span.style.color="red";
			bl2=false;
		}
	}
	*/
	
	function pwdCheck(){
		var pwd1=document.getElementById("users_pwd").value;
		var pwd2=document.getElementById("pwd2").value;
		var span=document.getElementById("checkPwd");
		if(pwd1==pwd2){
			span.innerHTML="일치";
			span.style.color="blue";
			bl2=true;
		}else{
			span.innerHTML="불일치";
			span.style.color="red";
			bl2=false;
		}
	}
	
	function pwdConfirm(){
		var pwd=document.getElementById("users_pwd").value;
		var span=document.getElementById("pwdCon");
		if(pwd.length<6||pwd.length>13){
			span.innerHTML="6~12자리 이루어진 비밀번호를 생성하세요.";
			span.style.color="red";
			bl3=false;
		}else{
			span.innerHTML="사용가능";
			span.style.color="blue";
			bl3=true;
		}
		if(special_pattern.test(pwd)|| blank_pattern.test(pwd)){
			span.innerHTML="특수문자나 공백을 사용할 수 없습니다.";
			span.style.color="red";
			bl3=false;
		}
	}	
	
	function regCancel(){
		window.history.back();
	}
	function delmsg(){
		var span1=document.getElementById("checkId");
		var span2=document.getElementById("checkPwd");
		var span3=document.getElementById("pwdCon");
		span1.innerHTML="";
		span2.innerHTML="";
		span3.innerHTML="";
	}
</script>