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
					<td><input type="text" name="users_id"><span id="checkId"></span></td>
				</tr>			
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="users_pwd"></td>
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
					<th colspan="2"><input type="submit" value="가입하기">
						<input type="button" value="가입취소" onclick="regCancel()">
					</th>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	function regCancel() {
		
	}

</script>
</html>