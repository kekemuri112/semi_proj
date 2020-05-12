<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/register/idsearch.jsp</title>
</head>
<body>
	<div>
		<form method="post" action="/idsearchController.do">
			<table>
				<tr>
					<th colspan="2" style="font-size: 30px">아이디 찾기</th>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="users_name">
					</td>
				</tr>		
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="users_email">
					</td>
				</tr>
				<tr>
					<th colspan="2" style="font-size: 30px">
						<input type="submit" value="찾기">
					</th>
				</tr>	
			</table>
		</form>
	</div>
</body>
</html>