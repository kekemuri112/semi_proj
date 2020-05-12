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
		<form method="post" action="/logincontroller.do">
			<table>
				<tr>
					<th colspan="2" style="font-size: 40px">로 그 인</th>
				</tr>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="users_id"></td>
				</tr>			
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="users_pwd"></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="로그인">
						<input type="button" value="뒤로가기" onclick="loginCancel()">
					</th>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>