<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form style="border-radius: 100px / 100px ; background-color: rgba(355,355,355,0.8);width:450px;margin: auto;margin-top: 180px;"  method="post" action="${cp }/reg/registercontroller.do" onsubmit="return check()">
		<table>
			<tr>
				<th colspan="2" style="color:#F45D00 ; font-size: 40px">회원 가입</th>
			</tr>
					<tr>
				<th>사용자 아이디</th>
				<td>
					<input type="text" name="users_id" id="users_id" placeholder="아이디 입력(6~14자리)" minlength="6" onkeyup="idsearch()" onfocusout="delmsg()" required>
					</td>

			</tr>
			<tr>
				<th></th>
				<td>
					<span id="checkId"></span>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="users_name" required>
					<span id="nameCon"></span>
				</td>
			</tr>			
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="users_pwd" id="users_pwd" placeholder="비밀번호 입력(6~12자리)" minlength="6" maxlength="12" onkeyup="pwdConfirm()" onfocusout="delmsg()" required>
					<br><span id="pwdCon"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" id="pwd2" onkeyup="pwdCheck()" maxlength="12" onfocusout="delmsg()" required>
					<br><span id="checkPwd"></span>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="users_email" id="users_email" onclick="delmsg()" required>
				<br><span id="emailCon"></span>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><select id="year" onchange="birth();">
			       <%for(int i=2015; i>=1980; i--){ %>
			       <option value="<%=i %>"><%=i %></option>
			       <%} %>
					</select>년
			    	<select name="month" id="month" onchange="birth();">
			       <%for(int i=01; i<=12; i++){ %>
			       <option value="<%=i %>"><%=i %></option>
			       <%} %>
					</select>월
					<select name="day" id="day" onchange="birth();">
			       <%for(int i=1; i<=31; i++){ %>
			       <option value="<%=i %>"><%=i %></option>
			       <%} %>
					</select>일
					<span id="checkBir"></span>
					<input type="hidden" name="users_birth" id="users_birth">
					</td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td><input type="text" name="users_phone" id="users_phone" onfocusout="delmsg()" required>
				<span id="phoneCon"></span>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="submit" value="가입하기" onclick="nameCheck(); emailCheck(); birthCheck(); viewBl();">
					<input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="button" value="가입취소" onclick="regCancel()">
				</th>
			</tr>
		</table>
		<br>
	</form>
</div>
<script type="text/javascript">
	var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	var blank_pattern = /[\s]/g;
	var users_id=document.getElementById("users_id").value;
	var bl=eval('false');
	var bl2=eval('false');
	var bl3=eval('false');
	var bl5=eval('false');
	var bl6=eval('false');
	var bl7=eval('false');
	var birthday="";

	
	function check(){
		if(bl&&bl2&&bl3&&bl5&&bl6){
			return true;
			//return true;
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
	
	function emailCheck(){
		var regExp= /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var xhr=null;
		var users_email=document.getElementById("users_email").value;
		var span=document.getElementById("emailCon");
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&&xhr.readyState==4){
				var xml=xhr.responseXML;
				var check=xml.getElementsByTagName("emailcheck")[0].firstChild.nodeValue;
				if(check=='false'){
					span.innerHTML="중복된 이메일 주소입니다."
					span.style.color="red";
					bl5=false;
				}else if(!users_email.match(regExp)){ 
					span.innerHTML="이메일 주소가 올바르지 않습니다.";
					span.style.color="red";
					bl5=false;
				}else{
					bl5=true;
				}
			}
		};
		xhr.open("post", "${cp }/reg/emailcheck.do?&users_email="+users_email, true);
		xhr.send();
	}
	
	function birth(){
		var year=document.getElementById("year").value;
		var month=document.getElementById("month").value;
		var day=document.getElementById("day").value;
		if(month<10){
			month='0'+month;
		}
		if(day<10){
			day='0'+day;
		}
		var birthday=year+month+day;
		document.getElementById("users_birth").value=birthday;
	}
	
	function birthCheck(){
		var users_birth=document.getElementById("users_birth").value;
		if(typeof users_birth=="undefined"||users_birth==null||users_birth==""){
			bl6=false;	
		}else{
			bl6=true;
		}
	}
	
	
	function regCancel(){
		window.history.back();
		
	}
	
	function delmsg(){
		var span1=document.getElementById("checkId");
		var span2=document.getElementById("checkPwd");
		var span3=document.getElementById("pwdCon");
		var span4=document.getElementById("emailCon");
		var span5=document.getElementById("phoneCon");
		span1.innerHTML="";
		span2.innerHTML="";
		span3.innerHTML="";
		span4.innerHTML="";
		span5.innerHTML="";
	}
</script>