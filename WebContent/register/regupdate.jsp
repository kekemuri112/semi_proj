<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div>
	<form method="post" action="${cp }/reg/updatecontroller.do" onsubmit="return check()">
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
				<th>기존 비밀번호</th>
				<td>
					<input type="password" name="old_pwd2" id="old_pwd2" onkeyup="oldCheck()" minlength="6" onfocusout="delmsg()">
					<span id="oldCon"></span>
				</td>
			</tr>
			<tr>
				<th>새 비밀번호</th>
				<td>
					<input type="password" name="users_pwd" id="users_pwd" onkeyup="pwdConfirm()" placeholder="비밀번호 입력(6~12자리)" minlength="6" maxlength="12" onfocusout="delmsg()">
					<span id="pwdCon"></span>
				</td>
			</tr>
			<tr>
				<th>새 비밀번호 확인</th>
				<td>
					<input type="password" id="pwd2" onkeyup="pwdCheck()" maxlength="12" onfocusout="delmsg()">
					<span id="checkPwd"></span>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="users_email" value="${vo.users_email}" placeholder="${vo.users_email}" ></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="text" name="users_birth" value="${vo.users_birth}" disabled="disabled"></td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td><input type="text" name="users_phone" value="${vo.users_phone}" placeholder="${vo.users_phone}"></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="hidden" name="users_num" id="users_num" value="${vo.users_num }">
					<br>
					<input type="submit" value="수정하기">
					<input type="hidden" value="수정취소" onclick="regCancel()"><br>
					
				</th>
			</tr>
		</table>
	</form>
</div>	

<script type="text/javascript">
	var pwd1='<c:out value="${vo.users_pwd }"/>';
	var bl1=eval('false');
	var bl2=eval('false');
	var bl3=eval('false');
	
	function check(){
		if(bl1&&bl2&&bl3){
			return true;
		}else{
			return false;
		}
	}
	function oldCheck(){
		var pwd2=document.getElementById("old_pwd2").value;
		var pwd3=document.getElementById("users_pwd");
		var span=document.getElementById("oldCon");
		if(pwd1==pwd2){
			span.innerHTML="비밀번호가 확인되었습니다.";
			span.style.color="blue";
			pwd3.focus();
			bl1=true;
		}else{
			span.innerHTML="비밀번호가 일치하지 않습니다.";
			span.style.color="red";
			bl1=false;
		}
	}
	
	/*function oldConfirm(){
		var xhr=null;
		var pwd1=document.getElementById("old_pwd1").value;
		var span=document.getElementById("oldCon");
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200 && xhr.readyState==4){
				var xml=xhr.responseXML;
				var check=xml.getElementByTagName("pwdcheck")[0].firstChild.nodeValue;
				if(check=='true'){
					span.innerHTML="확인되었습니다.";
					span.style.color="blue";
				}else{
					span.innerHTML-"비밀번호가 일치하지 않습니다.";
					span.style.color="red";

				}
			}
		};
		xhr.open("post","${cp }/reg/pwdcheck.do?&users_pwd="+users_pwd,true);
		xhr.send();
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
		var span1=document.getElementById("checkPwd");
		var span2=document.getElementById("pwdCon");
		span1.innerHTML="";
		span2.innerHTML="";
	}
</script>